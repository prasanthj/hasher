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

import static hasher.XXHash_64.hash;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for XXHash.
 */
public class TestXXHash {
  private static final long PRIME = 2654435761L;

  private final byte[] buffer;

  public TestXXHash()
  {
    buffer = new byte[101];

    long value = PRIME;
    for (int i = 0; i < buffer.length; i++) {
      buffer[i] = (byte) (value >> 24);
      value *= value;
    }
  }

  @Test
  public void testSanity()
      throws Exception
  {
    assertEquals(hash(buffer, 1, 0), 0x4FCE394CC88952D8L);
    assertEquals(hash(buffer, 1, PRIME), 0x739840CB819FA723L);

    assertEquals(hash(buffer, 4, 0), 0x9256E58AA397AEF1L);
    assertEquals(hash(buffer, 4, PRIME), 0x9D5FFDFB928AB4BL);

    assertEquals(hash(buffer, 8, 0), 0xF74CB1451B32B8CFL);
    assertEquals(hash(buffer, 8, PRIME), 0x9C44B77FBCC302C5L);

    assertEquals(hash(buffer, 14, 0), 0xCFFA8DB881BC3A3DL);
    assertEquals(hash(buffer, 14, PRIME), 0x5B9611585EFCC9CBL);

    assertEquals(hash(buffer, 32, 0), 0xAF5753D39159EDEEL);
    assertEquals(hash(buffer, 32, PRIME), 0xDCAB9233B8CA7B0FL);

    assertEquals(hash(buffer, buffer.length, 0), 0x0EAB543384F878ADL);
    assertEquals(hash(buffer, buffer.length, PRIME), 0xCAA65939306F1E21L);
  }

  @Test
  public void testEmpty()
      throws Exception
  {
    assertEquals(hash(new byte[]{}, 0, 0), 0xEF46DB3751D8E999L);
  }
}
