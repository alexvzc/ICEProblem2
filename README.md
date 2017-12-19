# Sorted file merging

> Problem 2: 
> 
> Given two sorted files, write a Java program to merge them to preserve sort order.
> 
> DO NOT assume either of these files can fit in memory!

### Build

Apache Maven 3.x and JDK 1.8.0 is required to build this project.

Once built, the target directory will contain a "iceproblem2.jar". This is an executable jar that can be used from the command line.

### Usage

```
java -jar iceproblem2.jar -i [file1] -i [file2] -o [outputfile]
```

Example (run from the project directory after building the project):

```
java -jar iceproblem2.jar -i src/main/resources/file1.txt -i src/main/resources/file2.txt -o target/output.txt
```

There's no output to the console, unless there is some error.

### Code review

The class mx.avc.iceproblem2.StreamMerger contains all the relevant code of merging two line sources into a single one.
