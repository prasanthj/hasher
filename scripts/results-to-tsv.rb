class Main
	public
	def self.run(file)
		valid_lines = get_benchmark_output_lines(file)
		bench128 = valid_lines.select { |v| v.include?("Benchmark128BitHash")}
		bench64 = valid_lines.select { |v| v.include?("Benchmark64BitHash")}
		bench32 = valid_lines.select { |v| v.include?("Benchmark32BitHash")}
		write_as_tsv(bench128, "Benchmark128BitHash.tsv")
		write_as_tsv(bench64, "Benchmark64BitHash.tsv")
		write_as_tsv(bench32, "Benchmark32BitHash.tsv")
	end

	private
	def self.get_benchmark_output_lines(file)
		result = []
		found_start = false
		File.open(file, 'r') do |f|
			f.each_line do |line|
				result.push(line) if found_start == true && line.include?(":megabytes")
				found_start = true if line.start_with?('Benchmark')
			end
		end
		result
	end

	def self.write_as_tsv(result, file)
		tsv = []
		tsv.push(["benchmark", "size", "throughput", "error"].join("\t"))
		result.each do |item|
			row = []
			cols = item.gsub(/\s+/m, ' ').strip.split(" ")
			row.push(cols[0].split(":").first.split(".").last)
			row.push(cols[1])
			row.push(cols[4])
			row.push(cols[6])
			tsv.push(row.join("\t"))
		end
		File.open(file, 'w') do |f|
			tsv.each do |line|
				f.write(line)
				f.write("\n")
			end
		end
	end
end

unless ARGV.length == 1
  puts "Usage: ruby results-to-tsv.rb jmh-results.txt\n"
  exit
end

input_file = ARGV[0]
Main.run(input_file)
