# ZAPL

## Instructions

To run the compiler, please download a copy of the release found [here](https://github.com/zana-a/zapl/releases/).

It is recommended to add the compiler to your path as an `alias`.

It can be done like so:

```sh
$ alias zapl="scala path/to/zapl-assembly-0.1.jar"
```

### Running a ZAPL file

To run a zapl file, please first create an empty directory for your code to live within.

Once done, you can call the compiler like so:

```text
Desktop
    -> project
        -> program.zapl
```

```elixir
def add(a: Int, b: Int): Int = do
  a + b
end

println(add(3, 5))
```

```sh
$ pwd
~/Desktop/project

$ zapl program.zapl
```

You should see an output of both the **AST**, and the Scala evaluated result like so:

```text
Complete! 🎉

AST: Program(
  List(
    Function(
      Identifier(
        "add"
      ),
      List(
        Parameter(
          Identifier(
            "a"
          ),
          Integer
        ),
        Parameter(
          Identifier(
            "b"
          ),
          Integer
        )
      ),
      Integer,
      Block(
        List(
          Pair(
            "+",
            Identifier(
              "a"
            ),
            Identifier(
              "b"
            )
          )
        )
      )
    ),
    FunctionCall(
      Identifier(
        "println"
      ),
      List(
        FunctionCall(
          Identifier(
            "add"
          ),
          List(
            Integer(
              1
            ),
            Integer(
              2
            )
          )
        )
      )
    )
  )
)
3
```

You should also notice that a new directory called target has been produced containing the Scala code generated from the
ZAPL source.

