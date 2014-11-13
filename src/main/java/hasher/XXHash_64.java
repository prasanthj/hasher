/**
 *   Copyright 2014 Prasanth Jayachandran
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hasher;

/**
 * XXHash 64-bit variant. https://code.google.com/p/xxhash/
 * This implementation is adapted from
 * https://github.com/airlift/slice/blob/master/src/main/java/io/airlift/slice/XxHash64.java
 */
public class XXHash_64 {
  private final static long PRIME64_1 = 0x9E3779B185EBCA87L;
  private final static long PRIME64_2 = 0xC2B2AE3D27D4EB4FL;
  private final static long PRIME64_3 = 0x165667B19E3779F9L;
  private final static long PRIME64_4 = 0x85EBCA77C2b2AE63L;
  private final static long PRIME64_5 = 0x27D4EB2F165667C5L;
  private final static long DEFAULT_SEED = 0;

  /**
   * XXHash 64-bit variant.
   *
   * @param data - input byte array
   * @return - hashcode
   */
  public static long hash(byte[] data) {
    return hash(data, data.length, DEFAULT_SEED);
  }

  /**
   * XXHash 64-bit variant.
   *
   * @param data   - input byte array
   * @param length - length of array
   * @param seed   - seed. (default 0)
   * @return - hashcode
   */
  public static long hash(byte[] data, int length, long seed) {
    long hash;
    int index = 0;
    if (length >= 32) {
      long v1 = seed + PRIME64_1 + PRIME64_2;
      long v2 = seed + PRIME64_2;
      long v3 = seed + 0;
      long v4 = seed - PRIME64_1;
      long limit = length - 32;
      do {
        long k1 = ((long) data[index] & 0xff)
            | (((long) data[index + 1] & 0xff) << 8)
            | (((long) data[index + 2] & 0xff) << 16)
            | (((long) data[index + 3] & 0xff) << 24)
            | (((long) data[index + 4] & 0xff) << 32)
            | (((long) data[index + 5] & 0xff) << 40)
            | (((long) data[index + 6] & 0xff) << 48)
            | (((long) data[index + 7] & 0xff) << 56);
        v1 = mix(v1, k1);
        index += 8;

        long k2 = ((long) data[index] & 0xff)
            | (((long) data[index + 1] & 0xff) << 8)
            | (((long) data[index + 2] & 0xff) << 16)
            | (((long) data[index + 3] & 0xff) << 24)
            | (((long) data[index + 4] & 0xff) << 32)
            | (((long) data[index + 5] & 0xff) << 40)
            | (((long) data[index + 6] & 0xff) << 48)
            | (((long) data[index + 7] & 0xff) << 56);
        v2 = mix(v2, k2);
        index += 8;

        long k3 = ((long) data[index] & 0xff)
            | (((long) data[index + 1] & 0xff) << 8)
            | (((long) data[index + 2] & 0xff) << 16)
            | (((long) data[index + 3] & 0xff) << 24)
            | (((long) data[index + 4] & 0xff) << 32)
            | (((long) data[index + 5] & 0xff) << 40)
            | (((long) data[index + 6] & 0xff) << 48)
            | (((long) data[index + 7] & 0xff) << 56);
        v3 = mix(v3, k3);
        index += 8;

        long k4 = ((long) data[index] & 0xff)
            | (((long) data[index + 1] & 0xff) << 8)
            | (((long) data[index + 2] & 0xff) << 16)
            | (((long) data[index + 3] & 0xff) << 24)
            | (((long) data[index + 4] & 0xff) << 32)
            | (((long) data[index + 5] & 0xff) << 40)
            | (((long) data[index + 6] & 0xff) << 48)
            | (((long) data[index + 7] & 0xff) << 56);
        v4 = mix(v4, k4);
        index += 8;
      } while (index <= limit);

      hash = Long.rotateLeft(v1, 1) + Long.rotateLeft(v2, 7) + Long.rotateLeft(v3, 12) +
          Long.rotateLeft(v4, 18);

      hash = update(hash, v1);
      hash = update(hash, v2);
      hash = update(hash, v3);
      hash = update(hash, v4);
    } else {
      hash = seed + PRIME64_5;
    }

    hash += length;

    // tail
    while (index <= length - 8) {
      int tailStart = index;
      long k = 0;
      int remaining = length - index;
      remaining = remaining > 8 ? 8 : remaining;
      switch (remaining) {
        case 8:
          k |= (long) (data[tailStart + 7] & 0xff) << 56;
        case 7:
          k |= (long) (data[tailStart + 6] & 0xff) << 48;
        case 6:
          k |= (long) (data[tailStart + 5] & 0xff) << 40;
        case 5:
          k |= (long) (data[tailStart + 4] & 0xff) << 32;
        case 4:
          k |= (long) (data[tailStart + 3] & 0xff) << 24;
        case 3:
          k |= (long) (data[tailStart + 2] & 0xff) << 16;
        case 2:
          k |= (long) (data[tailStart + 1] & 0xff) << 8;
        case 1:
          k |= (long) (data[tailStart] & 0xff);
      }
      hash = updateTail(hash, k);
      index += 8;
    }

    if (index <= length - 4) {
      int tailStart = index;
      int k = 0;
      int remaining = length - index;
      remaining = remaining > 4 ? 4 : remaining;
      switch (remaining) {
        case 4:
          k |= (long) (data[tailStart + 3] & 0xff) << 24;
        case 3:
          k |= (long) (data[tailStart + 2] & 0xff) << 16;
        case 2:
          k |= (long) (data[tailStart + 1] & 0xff) << 8;
        case 1:
          k |= (long) (data[tailStart] & 0xff);
      }
      hash = updateTail(hash, k);
      index += 4;
    }

    while (index < length) {
      hash = updateTail(hash, data[index]);
      index++;
    }

    hash = finalShuffle(hash);

    return hash;
  }

  private static long mix(long current, long value) {
    return Long.rotateLeft(current + value * PRIME64_2, 31) * PRIME64_1;
  }

  private static long update(long hash, long value) {
    long temp = hash ^ mix(0, value);
    return temp * PRIME64_1 + PRIME64_4;
  }

  private static long updateTail(long hash, long value) {
    long temp = hash ^ mix(0, value);
    return Long.rotateLeft(temp, 27) * PRIME64_1 + PRIME64_4;
  }

  private static long updateTail(long hash, int value) {
    long unsigned = value & 0xFFFF_FFFFL;
    long temp = hash ^ (unsigned * PRIME64_1);
    return Long.rotateLeft(temp, 23) * PRIME64_2 + PRIME64_3;
  }

  private static long updateTail(long hash, byte value) {
    int unsigned = value & 0xFF;
    long temp = hash ^ (unsigned * PRIME64_5);
    return Long.rotateLeft(temp, 11) * PRIME64_1;
  }

  private static long finalShuffle(long hash) {
    hash ^= hash >>> 33;
    hash *= PRIME64_2;
    hash ^= hash >>> 29;
    hash *= PRIME64_3;
    hash ^= hash >>> 32;
    return hash;
  }
}
