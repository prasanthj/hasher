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
package benchmarks;

import com.google.common.hash.Hashing;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

import de.greenrobot.common.hash.Murmur3A;
import hasher.FNV1;
import hasher.FNV1a;
import hasher.Murmur2;
import hasher.Murmur3;

/**
 *
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(3)
@Warmup(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
public class Benchmark32BitHash {

  @Benchmark
  public int murmur2_32(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return Murmur2.hash32(bytes, bytes.length, 0);
  }

  @Benchmark
  public int guava_murmur3_32(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return Hashing.murmur3_32().hashBytes(bytes).asInt();
  }

  @Benchmark
  public int greenrobot_murmur3a_32(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    Murmur3A hf = new Murmur3A();
    hf.update(bytes);
    return (int) hf.getValue();
  }

  @Benchmark
  public int murmur3_32(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return Murmur3.hash32(bytes, bytes.length, 0);
  }

  @Benchmark
  public int fnv1_32(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return FNV1.hash32(bytes, bytes.length);
  }

  @Benchmark
  public int fnv1a_32(BenchmarkData bd, ByteCounter bc) {
    byte[] bytes = bd.getBytes();
    bc.add(bytes.length);
    return FNV1a.hash32(bytes, bytes.length);
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(Benchmark32BitHash.class.getSimpleName())
        .forks(1)
        .build();

    new Runner(options).run();
  }
}
