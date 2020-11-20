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

## Class diagram

![diagram](http://www.plantuml.com/plantuml/svg/xLflRzks4txlJy7Vgr-Br0wQbqKHr7RYrLlQ5FbJOC20W9REDXTA9CXJugvhFlj4KQf5-sH5Sp5WWmCKSUyUaiU7zv1tHjvPv0QpHFPGe0HsTpSQNlroVlxf_vQD9RUMdGc-C3ovk_ijxVKOIta2LlC8s8oRUm3DlkPMnY87hco3CoFI-zBZVlfCnEmD4odMJ6wgt7YDENXHipzmcJb7hBAPXE-E8rRC-kBbJii2S1flMZxo13PjdzqgWT5k3RN4K4iDHULtKHg_zVo_vnCsCGBIs2w5titQ4LiZWmXeCqt7hGNpcZAUjF91ZBNLsFttVdPpsRRz0EoMf3t_EXAcbu_mgyhQD8EYqCo5YhWyumWt8gahf9p5HK7OWgq-KpgNZocOJQDRbQ8kf8HQWwAJMCFwDIYAzzSTGNkLU7ipxrEFCl_7-EzEjHiSa0m95v9w0EQ5aY-4nTphVhrTQ_kqVagUt3gmvFjqzrbgRksZCZ6rU6R1q2_J4VFxty_0uxX1aUUEZqtZv3iNMxcotdrx6dqgTqnbJRhvn0bBmWqawW6eu2qLl6qCtZO7J-Kd7RobWz__Id2pvdfjJrKwkLeJABmBscrj6J9jETIMCvesqaem6DPtWqmF_Znbl_C7FX1gS0r6S2d-uBvg8qgRpWDKln_U7syAUbztGf-Gw9pUhG7EHgpGDePOfyplzXq7Q3TtGB6hWjrl0ajiLhbx_7dGp598jvSECJJJGKVIF22CnpjsSkVoSIrdhaKWPQx5OEId6kfwHVtL8aSHVSiJFU4Y9JByqDyS-fj3VtFeR_xf-lhGtnpwcqD_S-Xl3ltDeRyvz3U7_gRtV44FuxZOo5YbQ9IKmJFqv2xaQf-Z356bDIr7IoKY6AbLWR0vP5nPtXQcKmhi4oP4kntLmNE0UCOZj-e5du6iIN39qrX25NMWJk-QY3Gkro2yFm5UFe1v4F18U2_KGcKOE4Ggi60MQkO-YuLrL7jE0ezMEfCSOHooiniJ1jpzMcveLFi-rt8Q7SDRVhA88jfSmaCNHiWDRbYKthFrkOA5i6Wu2fKs9wd7-kC8ShM2IEaei5Kf56RSKrbLMUThWlWNlcqX2zn4otMIty0A9m9al8NnyT08f_XQB9y5FFfu2Cn9C-Y8pOL4CBGcAk-7Byrw6LRK4hvHhj164H6Egng17JGs34-NW0Gp4wXCnnpBUQfJ9W3RtkuvcszUDALfuwtsLA-vgtgEIWaN0vanHonQGdJlJlzZZY787nlW20v07najTF9CkxDINRaKCUuKGGB2zFAMUcNYylcvSGSzLZ9BqggHULNMaAURy9Fduz71AhYJK0NU0fsqeaG2JGi7dV7ce9FEermojEunG3-RagDAL6V5gOHoQbV_MNMMNPzNGf0ktaRxt_ysYNnHYEwxa1WfjtwnqYw_hgcOLhN7cLei4_f-QAMylf-0BOlA0FsrH6Fz5vQMyzLWlahaO9eAf6fpLtVqCzrVmxdMKaH5BCvRVUwnzcUzozhqfT8sEimH0rfPWSe8i46Yr1s-pB-gB5ySa2oZ7CUaOoWbmN_NC3nhRN7ucxGnb6uEdpy_8FRtYFe3J_a231Ns4niDhy_lRgykrjyA423aPW7uNXLLaPlIVQHgtaYb2AiADPO2KXnx6xNBI08tta_oKB4wCIff9B3PgPN5dwrARrHZ9RrZyJw35KGPWjlA_aTHzPfxvj6eh1Xt08YkuaUdbRlPEWYpasBFLn7eBhB8VunOB97DbK8m-Ny_IPxYxOSB5li_WindJi5mX9ZDlh3hUy1eESN_iHTBH6tV71_F1CwoABSFb5aSwmHi0favjWLo8F1bRw8CIvG19jAvCabnUJ8-KnaoN0hBK2dvYca9t48X0nuXqqiXXTOmA2Tyjt2Fso1IIG_Ik0ptNVunI-HV)