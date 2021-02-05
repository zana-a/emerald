# ZAPL

## Syntax

### Module

```text 
# Modules by convention start with Uppercase alpha character
mod SomeModule do
  def function() = true
end
```

#### Call

```text
# Module names are appended by the :: operator
SomeModule::function()
```

### Function

```text
# Function names are snake cased by convention
def function_two() = do
  false
end

# Functions are also single lined unless introduced to blocks
def function_one() = true

def add(a, b) = a + b
```

#### Call

```text
# Functions are called with parenthesis
function_one()

# Functions can take many arguments
add(2, 3)
```
