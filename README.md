# Comp 127: Headline Generator Lab

Your tabloid newspaper is near bankruptcy, and you had to fire all your reporters.

Fortunately, you will use your programming skills to generate lascivious news headlines and save the business!

## Overview

The headline generator takes rules that look like this:

    [person] ESCAPES FROM [place]

The items in square brackets — `[person]` and `[place]` — are called _symbols_. Each symbol has a list of replacement choices. For example, three choices for `[place]` are:

    CAMPUS CENTER
    SPAM MUSEUM
    [person]’S BASEMENT

…and three choices for `[person]` are:

    BIGFOOT
    BEYONCÉ
    EINSTEIN

Your code randomly selects a substitution for each symbol to generate a headline, such as:

    BIGFOOT ESCAPES FROM BEYONCÉ’S BASEMENT!

## Orient yourself

Look in the `res/` folder, and inspect the various substitution rules that your code will use to generate headlines. Each file gives substitution choices for one “symbol,” e.g. `person.txt` lists all the choices for the `person` symbol.

Look at the `HeadlineGenerator` class. It contains _pseudocode_: an English description of the code you will need to write. Just skim it for now.

There is also a `Substitutions` class. You do not need to understand how it is implemented; the code uses techniques we have not covered in class. Fortunately, this class is an **abstraction**: it presents something complex in a simpler way. You only need to understand how to use its `getSubstitutionChoices()` method. Read the documentation for that method.

There is a `HeadlineGeneratorTest` class. Run it. All its test cases should fail.

## Implement the algorithm

Now implement the pseudocode in `HeadlineGenerator`. This is **too much to implement all at once**. You will need to find ways to **partially implement** it so that you can test your progress along the way!

To do this, you will mix **ad hoc manual testing** with **automated unit testing**.

You are free to implement all of this pseudocode in any order you like. The rest of this section is a _suggestion_ for how you might approach it. Your Possibly Wise Instructor™ suggests you follow the suggestion closely; however, you are welcome to follow it loosely or not at all.

### Implement `chooseRandomSubstitution()`

Find `chooseRandomSubstitution()` in `HeadlineGenerator`, and carefully read the pseudocode.

You are going to use `Substitutions.getSubstitutionChoices()` in this method. What is that method’s return type? Why? (Hint: the code represents each individual subsitution as a list, such as `["[person]", " TO WED ", "[person]"]`.)

Make sure you understand the types you are going to work with. Then try implementing the pseudocode.

Test your implementation of `chooseRandomSubstitution()` by adding a `main()` method to `HeadlineGenerator`, and performing some ad hoc tests such as:

```java
System.out.println(chooseRandomSubstitution("person"));
System.out.println(chooseRandomSubstitution("place"));
```

…which should give you output like this, but different every time:

```
[LOCH NESS MONSTER]
[[person], ’S PRIVATE ISLAND]
```

(Note that `println` doesn't print quotation marks around the strings, but it does print square brackets and commas to show that it's a list. That last line is a list with two strings; in code, it would be: `List.of("[person]", "’S PRIVATE ISLAND")`.)

Commit your work.

### Implement `applySubstitutions()`

Now delete the code inside your main method. You don’t need it anymore, and good programmers clean house.

Change your main method into a test of `applySubstitutions()`:

```java
System.out.println(
    applySubstitutions(
        List.of("ONE FISH", "[place]", "TWO FISH", "[person]")));
```

…and work on implementing `applySubstitutions()`. Even this method is a lot to implement all at once! Here’s a suggestion of how you could implement it piece by piece:

1. Just make it create a new list and copy all the strings into it, so that the output of your main method is:
    ```
    [ONE FISH, [place], TWO FISH, [person]]
    ```
2. Next make it detect symbols and replace them with the string `"???"`, so the output is:
    ```
    [ONE FISH, ???, TWO FISH, ???]
    ```
3. Now make it extract the symbol name by removing the braces, and add `"symbol: " + symbolName` to the list so the output is:
    ```
    [ONE FISH, symbol: place, TWO FISH, symbol: person]
    ```
4. Now make it call `generateText()` and add the results to the list, like the pseudocode says. Since `generateText()` just returns the list `["not implemented yet"]`, your output should look like this:
    ```
    [ONE FISH, not implemented yet, TWO FISH, not implemented yet]
    ```
5. Wouldn’t it be nice to verify that you’re calling `generateText()` with the correct symbol name? Try changing `generateText()` so that it returns a list with _two_ elements, `List.of("generateText", symbolName)`, so that the output looks like this:
    ```
    [ONE FISH, generateText, place, TWO FISH, generateText, person]
    ```

That’s good progress! Commit your work.

### Implement `generateText()`

This method puts together the other pieces you’ve built so far. Implement `generateText()`. This **will take very little code,** but it is still a big step — and worth testing carefully!

Again, delete the code inside of your main method, and instead write some code that will test `generateText()`. Think: What code can you put in your `main` method as an ad hoc test? What output do you expect?

Get it working, then commit your work.

### Run the unit tests

The tests in `HeadlineGeneratorTest` are **already correct**, and fairly comprehensive. **You do not need to change them!** Run them, and see if they all pass.

Fix any problems you find.

Commit your work.

### Make some headlines!

**Delete all the ad hoc test code** from your main method. It was only there to help you figure things out. Now you have the automated unit tests — and that's a much better way to check your code in the future than print statements in a main method.

Instead, write a loop to call `generateHeadline()` and print the result 100 times.

Run the program and enjoy your inevitable publication success!

## Optional fun task

Add your own entries to the various substitution files in `res/`. Have fun!

## Optional challenge task

Make it so that the headline generator keeps a running list of every substitution it has applied with a headline, and never applies the same one twice in the same headline (so that you don’t get e.g. “OBAMA TO WED OBAMA”). This is quite tricky!
