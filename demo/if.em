# simple if-statement for either a true or false value
if do
    1 == 2 -> do
        print("hello")
    end

    _ -> do
        print("this should print")
    end
end


# if-else statement. first true condition runs. if none, then nothing happens.
if do
    1 != 2 -> do

    end

    12 == 2 -> do

    end

    "hello" == 3 -> do

    end

    1 != 2 -> do

    end
end
