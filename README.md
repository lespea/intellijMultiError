### Overview

Occassionally I have jars I need to put on my classpath that are not available externally (ie can't fetch via ivy).
I also have a need to mark some of them as "provided" so throwing them in `lib/` doesn't really work.  Via this
[stackoverflow post](http://stackoverflow.com/questions/19290812/publish-jar-files-with-sbt-3rd-party) I learned
that it is possible to easily have sbt treat a jar file as the compiled source for a sub-module.  The problem is
that while this works fine using sbt, IntelliJ doesn't recognize the classes contained within while working on
a module that deponds and that "fake" module.

### Description of example

I have three sub-modules here.

1. `externalJarMaker` -- is used soley to create jar files that we will treat as if they
were private and predent we didn't have the source.
2. `jarLinker` -- uses the aforementioned trick which links to the jar files
3. `testApp` -- simply tries using an object from within `externalJarMaker`.

### Steps to reproduce

Works:

```
sbt
project testApp
run
```

Fails:

1. Launch IntelliJ
2. File->Open->Select this folder
3. Navigate to the `testApp` module and open `Runner`
4. Notice the red-lines complaining that `tester.TestStr` is not resolvable
5. Click the icon next to `object Runner` and select `Run`; notice it fails to compile
