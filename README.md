Comp 127: Headline Generator Lab
====

Your tabloid newspaper is near bankruptcy, and you had to fire all your reporters.

Fortunately, you will use your programming skills to generate lascivious news headlines and save the business!

Overview
---

The headline generator takes rules that look like this:

    [person] ESCAPES FROM [place]

The items in square brackets — `[person]` and `[place]` — are called _symbols_. Each symbol has a list of replacement choices. For example, three choices for `[place]` are:

    KAGIN
    SPAM MUSEUM
    [person]’S BASEMENT

Your code randomly selects a substitution for each symbol to generate a headline.

Setup
---

Please carefully follow the **COMP 127 Homework / Lab Procedure** on Moodle. Don’t miss any steps!

Tasks
---

1. Look in the `res/` folder, and inspect the various substitution rules that your code will use to generate headlines. Each file gives substitution choices for one “symbol,” e.g. `person.txt` is the choices for the `person` symbol.
1. Look at the `HeadlineGenerator` class. It contains _pseudocode_: an English description of the code you will need to write.
1. There is also `Substitutions` class. You do not need to understand how it is implemented; the code uses techniques we have not covered in class. You do, however, need to understand how to use the `getSubstitutionChoices()` method. Read the documentation for that method.
1. There is a `HeadlineGeneratorTest`. Run it. It should fail.
1. Now implement the pseudocode in `HeadlineGenerator`. It is probably going to be **too much to implement all at once**. Try to find ways to **partially implement** it so that you can test your progress along the way. Here are some examples of how you might do that:
    - Make `applySubstitutions()` just pass through whatever it gets, without processing it yet.
    - Implement `chooseRandomSubstitution()`.
    - Make `applySubstitutions()` detect symbols in square braces (`"[…]"`), but instead of trying to process them, just replace them with the string `"symbol found"`.

When the tests pass, try changing the `HeadlineGenerator.main()` method to print 100 headlines instead of 10.

Try adding more rules in the `res` directory. Have fun!


Optional Challenge Tasks
---

Make it so that the headline generator keeps a running list of every substitution it has applied, and never applies the same one twice (so that you don’t get e.g. “OBAMA TO WEB OBAMA”). This is quite tricky!
