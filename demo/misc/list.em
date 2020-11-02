some_list = [[1, 2, 3], 1]

some_list
~> List.get(0) # [1, 2, 3]
~> List.get(2) # 3

some_list
~> List.pop() # [1]
~> List.push({a: 2}) # [%{a: 2}, 1]

some_list
~> with_index()
# [
#   {[1, 2, 3], 0},
#   {1, 1}
# ]


a_val = "some value"

some_list
~> List.insert_to(0, a_val)
