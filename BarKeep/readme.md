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
to use a database as a solution. This also makes it easier to expand to use an ORM in the future
as well to extend it to interface with a rest api.


The app itself is using "implicite storage" because the user never has to manage big sets of data.
This gives a better sence of "flow" and makes it easier to manage the userdata when the user should
be able to log in and see its own data.

There is also a json module that is not being used at this time because it could be used at a later date for
storing local data that is particular to the device such as settings.


## FxGUI

The FxGUI module handles the GUI interaction with the user,
and communicates with the Core package as backend.

Module code can be found:\
`./fxgui/src` ([Here](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2011/gr2011/-/tree/master/BarKeep/fxgui/src))


## Component diagram

<p align="center"><img src="http://www.plantuml.com/plantuml/png/TL5DQm8n4BtFhnZIyruyYeZrGoe52rfFonxoCThTpMP24g4W-z-R5OL5w1n2vhtalPVC8YOPqhMpuALkvOLGQ0uay0Q8fPAX9V8L5gmQqYaEe959AXafe-Wfn3ecSfe0oYQogt0sstF2KoGpdqCFuDWGYlFlvLeFikMANGfiRRwQ3nS5bk_ULozuWK_7LtnicTNX6GV5nmGVpIF_cQsXWE9RAajHe8nerC3aAa_7pU6ORTU7tVvpsEo-JddT7dQR_JhtYFrTV_pUOZu8JB4nIaz_9W2nhPsNGNQekVFioAKRR7FW5LiEuv_0j-MzOCd1KFYVxs51pkI9_040" alt="Diagram"></p>