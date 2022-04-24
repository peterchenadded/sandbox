# Overview

Notes around path finding

# Library summary

* Networkx - too slow 5 seconds for 500x500
* Igraph - no zero boundary
* Networkit - no lattice, heuristic not dynamically calculated
* Tcod - very quick, no way to change heuristic
* Jgrapht - can't create large grid e.g. 500x500
* Pystar2d - very quick for 500x500, can't change heuristic

# tcod

```
pip install tcod
import numpy as np
import tcod
cost = np.ones((500,500), np.int8)
pf = tcod.path.AStar(cost=cost, diagonal=0)
s = time.time()
path = pf.get_path(0, 0, 499, 499)
end = time.time() - s
print('%s %s' % (end, len(path)))
```

# jgrapht

```
pip install jgrapht
import jgrapht
g = jgrapht.create_graph(directed=False, weighted=True)
jgrapht.generators.grid(g, 10, 10)
s = time.time()
r = jgrapht.algorithms.shortestpaths.a_star(g, 0, 99, lambda x, y: y, use_bidirectional=False)
end = time.time() - s
print(r.vertices)

g = jgrapht.create_graph(directed=False, weighted=True)
jgrapht.generators.grid(g, 500, 500)
s = time.time()
r = jgrapht.algorithms.shortestpaths.a_star(g, 0, 249999, lambda x, y: y, use_bidirectional=False)
end = time.time() - s
print(len(r.vertices))
```

# pyastar2d

```
pip install pyastar2d

import numpy as np
import pyastar2d
import time
costs = np.ones((10,10), np.float32)
path = pyastar2d.astar_path(costs, (0, 0), (9,9), allow_diagonal=False)
print(path)

costs = np.ones((500,500), np.float32)
s = time.time()
path = pyastar2d.astar_path(costs, (0, 0), (499,499), allow_diagonal=False)
print(len(path))
print(time.time()-s)
```

# Heuristics

http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html

# plotting

```
import numpy as np
import matplotlib.pyplot as plt

g = np.ones((10,10), np.int8)
g[1][0] = 100
plt.matshow(g)

```
