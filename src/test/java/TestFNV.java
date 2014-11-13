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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hasher.FNV1;
import hasher.FNV1a;

/**
 * Few tests from http://www.isthe.com/chongo/src/fnv/test_fnv.c
 */
public class TestFNV {
  String empty = "";
  String a = "a";
  String b = "b";
  String c = "c";
  String d = "d";
  String e = "e";
  String f = "f";
  String fo = "fo";
  String foo = "foo";
  String foob = "foob";
  String fooba = "fooba";
  String foobar = "foobar";
  String url1 = "http://antwrp.gsfc.nasa.gov/apod/astropix.html";
  String url2 = "http://en.wikipedia.org/wiki/Fowler_Noll_Vo_hash";

  @Test
  public void testFNV1_32() {
    assertEquals(0x811c9dc5, FNV1.hash32(empty.getBytes()));
    assertEquals(0x050c5d7e, FNV1.hash32(a.getBytes()));
    assertEquals(0x050c5d7d, FNV1.hash32(b.getBytes()));
    assertEquals(0x050c5d7c, FNV1.hash32(c.getBytes()));
    assertEquals(0x050c5d7b, FNV1.hash32(d.getBytes()));
    assertEquals(0x050c5d7a, FNV1.hash32(e.getBytes()));
    assertEquals(0x050c5d79, FNV1.hash32(f.getBytes()));
    assertEquals(0x6b772514, FNV1.hash32(fo.getBytes()));
    assertEquals(0x408f5e13, FNV1.hash32(foo.getBytes()));
    assertEquals(0xb4b1178b, FNV1.hash32(foob.getBytes()));
    assertEquals(0xfdc80fb0, FNV1.hash32(fooba.getBytes()));
    assertEquals(0x31f0b262, FNV1.hash32(foobar.getBytes()));
    assertEquals(0xb4448d60, FNV1.hash32(url1.getBytes()));
    assertEquals(0x025dfe59, FNV1.hash32(url2.getBytes()));
  }

  @Test
  public void testFNV1_64() {
    assertEquals(0xcbf29ce484222325L, FNV1.hash64(empty.getBytes()));
    assertEquals(0xaf63bd4c8601b7beL, FNV1.hash64(a.getBytes()));
    assertEquals(0xaf63bd4c8601b7bdL, FNV1.hash64(b.getBytes()));
    assertEquals(0xaf63bd4c8601b7bcL, FNV1.hash64(c.getBytes()));
    assertEquals(0xaf63bd4c8601b7bbL, FNV1.hash64(d.getBytes()));
    assertEquals(0xaf63bd4c8601b7baL, FNV1.hash64(e.getBytes()));
    assertEquals(0xaf63bd4c8601b7b9L, FNV1.hash64(f.getBytes()));
    assertEquals(0x08326207b4eb2f34L, FNV1.hash64(fo.getBytes()));
    assertEquals(0xd8cbc7186ba13533L, FNV1.hash64(foo.getBytes()));
    assertEquals(0x0378817ee2ed65cbL, FNV1.hash64(foob.getBytes()));
    assertEquals(0xd329d59b9963f790L, FNV1.hash64(fooba.getBytes()));
    assertEquals(0x340d8765a4dda9c2L, FNV1.hash64(foobar.getBytes()));
    assertEquals(0xcb27f4b8e1b6cc20L, FNV1.hash64(url1.getBytes()));
    assertEquals(0x26caf88bcbef2d19L, FNV1.hash64(url2.getBytes()));
  }

  @Test
  public void testFNV1a_32() {
    assertEquals(0x811c9dc5, FNV1a.hash32(empty.getBytes()));
    assertEquals(0xe40c292c, FNV1a.hash32(a.getBytes()));
    assertEquals(0xe70c2de5, FNV1a.hash32(b.getBytes()));
    assertEquals(0xe60c2c52, FNV1a.hash32(c.getBytes()));
    assertEquals(0xe10c2473, FNV1a.hash32(d.getBytes()));
    assertEquals(0xe00c22e0, FNV1a.hash32(e.getBytes()));
    assertEquals(0xe30c2799, FNV1a.hash32(f.getBytes()));
    assertEquals(0x6222e842, FNV1a.hash32(fo.getBytes()));
    assertEquals(0xa9f37ed7, FNV1a.hash32(foo.getBytes()));
    assertEquals(0x3f5076ef, FNV1a.hash32(foob.getBytes()));
    assertEquals(0x39aaa18a, FNV1a.hash32(fooba.getBytes()));
    assertEquals(0xbf9cf968, FNV1a.hash32(foobar.getBytes()));
    assertEquals(0xce524afa, FNV1a.hash32(url1.getBytes()));
    assertEquals(0xdd16ef45, FNV1a.hash32(url2.getBytes()));
  }

  @Test
  public void testFNV1a_64() {
    assertEquals(0xcbf29ce484222325L, FNV1a.hash64(empty.getBytes()));
    assertEquals(0xaf63dc4c8601ec8cL, FNV1a.hash64(a.getBytes()));
    assertEquals(0xaf63df4c8601f1a5L, FNV1a.hash64(b.getBytes()));
    assertEquals(0xaf63de4c8601eff2L, FNV1a.hash64(c.getBytes()));
    assertEquals(0xaf63d94c8601e773L, FNV1a.hash64(d.getBytes()));
    assertEquals(0xaf63d84c8601e5c0L, FNV1a.hash64(e.getBytes()));
    assertEquals(0xaf63db4c8601ead9L, FNV1a.hash64(f.getBytes()));
    assertEquals(0x08985907b541d342L, FNV1a.hash64(fo.getBytes()));
    assertEquals(0xdcb27518fed9d577L, FNV1a.hash64(foo.getBytes()));
    assertEquals(0xdd120e790c2512afL, FNV1a.hash64(foob.getBytes()));
    assertEquals(0xcac165afa2fef40aL, FNV1a.hash64(fooba.getBytes()));
    assertEquals(0x85944171f73967e8L, FNV1a.hash64(foobar.getBytes()));
    assertEquals(0x6be396289ce8a6daL, FNV1a.hash64(url1.getBytes()));
    assertEquals(0xd9b957fb7fe794c5L, FNV1a.hash64(url2.getBytes()));
  }
}
