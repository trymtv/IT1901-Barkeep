# Barkeep Source

This project consists of multiple modules:

- core
- fxgui


## Core

The Core module contains code to handle the core-functionality

Module code can be found:\
`./core/src` ([Here](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2011/gr2011/-/tree/master/BarKeep/core/src))

### Storage

Under core there is a module named database that takes care of all the storage.

This because the data the app is using has a lot of relations to be managed and we decided
to use a database as a solution.

The app itself is using "implicite storage" because the user never has to manage big sets of data.
This gives a better sence of "flow" and makes it easier to manage the userdata when the user should
be able to log in and see its own data.



## FxGUI

The FxGUI module handles the GUI interaction with the user,
and communicates with the Core package as backend.

Module code can be found:\
`./fxgui/src` ([Here](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2011/gr2011/-/tree/master/BarKeep/fxgui/src))


## Package diagram

<p align="center"><img src="http://www.plantuml.com/plantuml/png/NP11IyGm48Nlyok6tliFH7Qn5OgmAztr8A67QNTSgh49oHG5wNytfWsowQ7CvCjxURoT5t8oVsgmD7pGbR5u-xxEOu4_e1xC9CvepSvtU5UMgFRMTX3_6ItBUrGjM-D7CMvaty42y6yw6CS5aiU_8NXZ-2uar9CFz4ozPX-vn76xeLvETNlgrhbgcyDJMfhZ5x-QUTiUswO-L94jeAhpqVJlFCYQEWIh9nabXbBxvoQTLjlxoJ-jc6aoiYQC6bYo6ZBbRICvtV5q2St_0W00" alt="Diagram"></p>
