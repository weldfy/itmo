# Test log [windows]

## More info and test log [ubuntu] in Github.Actions: [![statusbadge](../../actions/workflows/buildtest.yaml/badge.svg?branch=main&event=pull_request)](../../actions/workflows/buildtest.yaml)

Build log (can be empty):
```

```

Stdout+stderr (./omp4 0 in out):
```
OK [program completed with code 0]
    [STDERR]:  
    [STDOUT]: Time(12 thread(s)) : 7.7343 ms

```
     
Stdout+stderr (./omp4 -1 in out):
```
OK [program completed with code 0]
    [STDERR]:  
    [STDOUT]: Time(12 thread(s)) : 7.9416 ms

```

Input:
```
1 100000

```

Output 0:
```
3.15636

```

Output -1:
```
3.1552

```