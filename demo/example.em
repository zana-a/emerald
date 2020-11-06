name := "Zana"
a_list := [1, 2, 3]


mod Module
  fn hello(name)
    "Hello " + name
  nf

  fn factorial(n)
    if (n >= 1) -> n * factorial(n - 1)
     | true -> 1
    fi
  nf
dom

if name == "Zana" -> do true -> print(Module.hello(name)) od
 | true -> print(Module.factorial(1))
fi

if a_list[0] == 1 -> print(true) fi
