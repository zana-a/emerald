name := "Zana"

mod Module
  fn hello(name)
    "Hello " + name
  nf
dom

if name == "Zana" -> do true -> print(Module.hello(name)) od
 | true -> print(Module.hello("Bob"))
fi

