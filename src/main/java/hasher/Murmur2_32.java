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
 * Murmur2 32-bit variant.
 * Java port of https://code.google.com/p/smhasher/source/browse/trunk/MurmurHash2.cpp#37
 */
public class Murmur2_32 {
  private static final int M = 0x5bd1e995;
  private static final int R = 24;
  private static final int DEFAULT_SEED = 0;

  /**
   * Murmur2 32-bit variant.
   *
   * @param data - input byte array
   * @return - hashcode
   */
  public static int hash(byte[] data) {
    return hash(data, data.length, DEFAULT_SEED);
  }

  /**
   * Murmur2 32-bit variant.
   *
   * @param data   - input byte array
   * @param length - length of array
   * @param seed   - seed. (default 0)
   * @return - hashcode
   */
  public static int hash(byte[] data, int length, int seed) {
    int h = seed ^ length;
    int len_4 = length >> 2;

    // body
    for (int i = 0; i < len_4; i++) {
      int i_4 = i << 2;
      int k = (data[i_4] & 0xff)
          | ((data[i_4 + 1] & 0xff) << 8)
          | ((data[i_4 + 2] & 0xff) << 16)
          | ((data[i_4 + 3] & 0xff) << 24);

      // mix functions
      k *= M;
      k ^= k >>> R;
      k *= M;
      h *= M;
      h ^= k;
    }

    // tail
    int len_m = len_4 << 2;
    int left = length - len_m;
    if (left != 0) {
      if (left >= 3) {
        h ^= (int) data[length - 3] << 16;
      }
      if (left >= 2) {
        h ^= (int) data[length - 2] << 8;
      }
      if (left >= 1) {
        h ^= (int) data[length - 1];
      }

      h *= M;
    }

    // finalization
    h ^= h >>> 13;
    h *= M;
    h ^= h >>> 15;

    return h;
  }
}
