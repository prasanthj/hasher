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
 * Murmur2 64-bit variant.
 * Java port of https://code.google.com/p/smhasher/source/browse/trunk/MurmurHash2.cpp#96
 */
public class Murmur2_64 {
  private static final long M = 0xc6a4a7935bd1e995L;
  private static final int R = 47;
  private static final int DEFAULT_SEED = 0;

  /**
   * Murmur2 64-bit variant.
   *
   * @param data - input byte array
   * @return - hashcode
   */
  public static long hash(final byte[] data) {
    return hash(data, data.length, DEFAULT_SEED);
  }

  /**
   * Murmur2 64-bit variant.
   *
   * @param data   - input byte array
   * @param length - length of array
   * @param seed   - seed. (default 0)
   * @return - hashcode
   */
  public static long hash(final byte[] data, int length, int seed) {
    long h = (seed & 0xffffffffl) ^ (length * M);
    int length8 = length >> 3;

    // body
    for (int i = 0; i < length8; i++) {
      final int i8 = i << 3;
      long k = ((long) data[i8] & 0xff)
          | (((long) data[i8 + 1] & 0xff) << 8)
          | (((long) data[i8 + 2] & 0xff) << 16)
          | (((long) data[i8 + 3] & 0xff) << 24)
          | (((long) data[i8 + 4] & 0xff) << 32)
          | (((long) data[i8 + 5] & 0xff) << 40)
          | (((long) data[i8 + 6] & 0xff) << 48)
          | (((long) data[i8 + 7] & 0xff) << 56);

      // mix functions
      k *= M;
      k ^= k >>> R;
      k *= M;
      h ^= k;
      h *= M;
    }

    // tail
    int tailStart = length8 << 3;
    switch (length - tailStart) {
      case 7:
        h ^= (long) (data[tailStart + 6] & 0xff) << 48;
      case 6:
        h ^= (long) (data[tailStart + 5] & 0xff) << 40;
      case 5:
        h ^= (long) (data[tailStart + 4] & 0xff) << 32;
      case 4:
        h ^= (long) (data[tailStart + 3] & 0xff) << 24;
      case 3:
        h ^= (long) (data[tailStart + 2] & 0xff) << 16;
      case 2:
        h ^= (long) (data[tailStart + 1] & 0xff) << 8;
      case 1:
        h ^= (long) (data[tailStart] & 0xff);
        h *= M;
    }

    // finalization
    h ^= h >>> R;
    h *= M;
    h ^= h >>> R;

    return h;
  }
}
