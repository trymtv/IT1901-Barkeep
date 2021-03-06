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

![diagram](http://www.plantuml.com/plantuml/svg/xLnVRnkv4N_tfs3HXoR8QOFpOt088ibsuzOv1xPpHO421hKxabXpoGN9jULUSv-zooMtMaeZMfkLZJQt0G9RCpyEX_E7v92KFsX3bIbpVcIOuK1kRoVZgx-Uddx-aoPJJhKc9umk5CrlR_yXZeu84JG7NT0KI4ODdL4DvDUAJ4XQenjYSgAOkBi6TSzIXt3_tf0CE1XuzRf5Me3P_3nv_9dc8Oep7S8qlOUG05Ibow3froFtlulgPug1oFII5PZ-DCkixFRdTOCzEQaKzw3CZJovkNpA4bu4HdQIGvQ2NDx3VgJ9unSDIdSn5HC6rBpDI3wo6IX13TGklu92QcQaUcnPxLSwqqRHr7oj-fupaJaVdI-4L32b6gPI6Ce44mkd3DxgZ2aTHaPldTTcV5BnqEeJC0y0mkiHnpex_tYfx8_ZGwb-Vla0VvVbVWOVSpvvl7mGe12DGvHLyb0wMbdxHeJ5YY3fOoWiTdhal3VcdWcF9VC5qowXTIJLkys3yW6q7Mz0B2j2fQpkCTXhq9f9yOaAkW3LzeesrB3qAnaHFIVlY0UUqTIwRmlcL0v1FTIoGzrsm8WK5Uf1gknKf383LUdrEQGSp3cu9_3FIC1Q3jFN5ScoaCKBMQi28Q7TyBLKZaPxpBtlI1PIZaXiWSE5hd61v7b8xYW-SFCxKgu_EHbeFkBwUL2W7DkauLAxgeM0NEluZ6Sb1OGm7sZ1NAItQAaKSxRmP9yZ5fWuHgt22HZAk3xF2zu0VLXjS5rKjSDsN9gbLCmmqDlC9g-sELOkJWqpmEV0E4rbASpFKfok2gOWYo0kP7g70AOACX26KQuZOau5dV4M_MkOHOUqjlSxOdGkQMQ1GJO_Zpv_WzarfALYvd4QGltyLxdNMYsbXadnMSbxbcrvBfXGjxZbrf03m1-zZgMAql7Hnm8vAOlat--9L8j45ug9nLpH72ezxnBjXrmrLP1O1JFNi2qaBjrcwuogEu2YvldCn1k5tkwjcPUrLr_VDgkGU8oCocBqv01SOog4D7Nm90ymyzHIEKeWsJ50ZJDQ6533p3RCRCFCDinit-7CjbTm6oKv1uK7k6UwdVmq1EF52ruaEg8LCdaypp2wEnx1E7g3o4G6AqyBgvBDj2aoQhedVaVRjCe6r43uYLhig2muwqBQFTT0V9KH6uM7eDv6UCXP07uQy7o3idtZ8tAsHzPZpWCYKZK7NbemRK1PDXgKbX4RPZpJtEo35jI-L0wcCZmk4N5umHiKoJq4nsfIFEWGIKZbkwwJEylhhfc5TXU3HcuGd1EtvesBmh6Ge3_qJlI5zs3FlyN2Uth-zUxbrv2Nd-zVVZAETUTtY44MUPhpakDqRzso-G54L0Untop5-w5l-pugMCVqQenttkFIzP0Ne8U_8NpEIxpdlMKx_DNOGQ-k3dOjrFiYvtTJA0-5ylUs2nzsuIJS8pHfy4zwJnCcamh6A6V_eZFUmhPCsht1ZYJziHFw64NRjNMpWQMXFSJR8FruLyL6ZA2jxD34FX4GB39OQLzqu51ygiCsEnrqs6qEH9Du0wTFm8ZPxhkz3n_kmxiNAUvT2Cli-8HfaQoeYdXjx0hNejzJNbf69aiNIytPKYtrrUiePG7cFDkak5FMaFQBxIKWwgsc6ccgiQP6kb4uOl-w3BMQwmev6usGKNFK3zmmul5Jzi3Qwg1jtu9o_-7xPq0uv__E23wxdyePm-gSkP0fvIVKm0tBsnaI7aVZD3_KFLF7FqCCYRPNp7IXIR0-C7CYVMZN1sRYW-GTil8Tnl8kz33ozsF__iSjpgAGKyQn1S2jqVOPOCMrFtvQv_Rx5iulbssWj_Txn9V88qMiSxlauIlJ6-B6tuz0isn7HfvQUs1Fnvqjjc9bFNeyZF0d5I5znxD85ExkvZ3bDQQytgcyHfIFnIUgZSQLxpyb-5rl95IHuEeGG6ukw4ABHyXvtAbn7s7cZhno7GhCeS3ydoWmRJnsk7547iFdb0aamb-wlXdgcw6-6UgReRuPwfkXlXdgcw6-6UgReRuPwfkXlZdQFw77MRRxgVflTa9knZafZP6YbSlfKh8K9d9L8tG5cJQKdshIUmpi0YP4sn6rmNE0R4PJs-i5dG5lfU2IYen3etMGdOvrnaJc-q2uVmPpUM-liU01uLx8XInDm622cTgouCXUmunfQsfdau1JoABarC0qj4uS4ohSVLhUKQYENAnLPhGMtkPZcoYap4Ku-CR8K6KsABBeMVfSrS_5tPkKtK7giCuTOQnw2EVM1DflXOA8itn_isjvko1kXN-g8_U4VHtaDx0oPmnujeLn-k08c_4jNNvXyE3qGJ37ECWvOCwu0ONdHCFzzBZc8Odcc0XlKcLeF8b2nbNBW1roR1nw5u44aX6K-rgBb-EzZ02skTrZjXeysnMcwApcMAqzTxEVmqB2we16p1kIBY6zizwtPpA8yQSA_1qzxZ8yqL6VThTAC-MYXXdzzzimNx1L9lFBpurrz5JoCXTD8VE3tqE-tuGVxuyt5jR0RGeru2tGSHpbYs2yux0otcnqtBcL3OPzn-ZGUvlaJPDK9xLNd3_sp_wJnfUzbbUNa5RVdVHlFqr2LnIa-gvJ34qtTpmRunUjBCQpsk5KIvSpP7xOn-JjyGJMqaOgm8ybOlk_rjPolahcgvmdvu8PR6zkznt-lQc7qwBW_fkxbjlyxjlWothdleD9fKFhC4PKypQ9WGu2fSr8q9Vio3OAdpxTj-CG-tTLmrijheUVINUgqetXukS0kdyqfeZy2QBVo6HyVNhxvUfYVIg0W0nL2p0VPRqhiYBjhzYUDvN2mAf1JJa3OQQEXeqovK2Lvxl1ubOzKpBVQS3Tp49gynSjnOtSkPEET3uYi8AqD627SW1N-6c_PnnD_8un0i2hYeE6bPtPUYHcD6DFLoaKNTAY-gVOOcd8N4htJVBFd0hpvTC5oTpVLQma2r3KG4PcZ-Jw3aowF3T_8A-MnXJwtTktCsPcPLhH4waMRuiST0wbUgjhPCBCwwCpgKWk5H0cvbBbzUH9w4oMXfWbqyH8oNyW1GUgeKu3cXfIB1bdHG695_XXOHUt99Nv4OZCg_i1H5Rc_3y0)