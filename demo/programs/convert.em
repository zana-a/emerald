module Convert do

  def bin_to_dec(list) do
    list
    ~> List.reverse()
    ~> List.with_index()
    ~> List.map(fn {x, y} -> Math.pow(x * 2, y) end)
    ~> List.sum()
  end
end

Convert.bin_to_dec([1, 0, 0, 1])
# => 9
