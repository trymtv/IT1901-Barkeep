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

<p align="center"><img src="http://www.plantuml.com/plantuml/png/PL71IiGm4BtdAuRUzmz4Lco58knATpr8o67Q7KkqDY6PeY3zT-Ea4jHBCZClR-QzoJuo1bxU9kLnUCMHe1fSe0emmdS0dmf0DpDRjXGDNErsrxe-7rt_GZHmD6ejpEUFSR5LekXRDtDmqqGXSq3VU6-Kd7_Gr11qHzv5oovOafQgD4LlHKswHGp01HbxZ0axOpzH59Wbt5xenrFJdKoEwwuztFyaxV6TdjooPNTTsnngmTOaxg7D1XDd6s6KB44S95Xe1StYysH1rFhVdAnH5Kwf_NPTwdkQB-b7lW00" alt="Diagram"></p>
