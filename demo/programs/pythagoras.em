# a = IO.gets("a>")
# b = IO.gets("b>")
# c = IO.gets("c>")
#
# d = (Math.pow(String.to_integer(String.trim(a)), 2) +
#   Math.pow(String.to_integer(String.trim(b)), 2)) ==
#    Math.pow(String.to_integer(String.trim(c)), 2)
#
# e
# ~> IO.puts()
#
# a
# ~> String.trim()
# ~> String.to_integer()
# ~> Math.pow(2)
# ~> Math.puts()

defmodule Pythagoras do
  def run() do
    a =
      IO.gets("a>")
      |> clean()

    b =
      IO.gets("b>")
      |> clean()

    c =
      IO.gets("c>")
      |> clean()

    (a + b)
    |> is_pythagorean(c)
  end

  def is_pythagorean(left, right) do
    left == right
  end

  def clean(string) do
    string
    |> String.trim()
    |> String.to_integer()
    |> Math.pow(2)
  end
end

Pythagoras.run()
|> IO.puts()
