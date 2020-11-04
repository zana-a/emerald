name := "Zana"

mod Module
  fn hello(name)
    "Hello " + name
  nf
dom

if name == "Zana" -> print(Module.hello(name))
 | true -> print(Module.hello("Bob"))
fi

