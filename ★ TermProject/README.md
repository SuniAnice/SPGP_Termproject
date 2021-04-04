# 스마트폰 게임 프로그래밍

## Term Project 1차 발표

------

개발 컨셉: 캔디 크러쉬 사가와 유사한 3-매치 퍼즐 게임에 RPG 요소를 더한 게임

플레이어는 자신의 턴에 지정된 횟수 만큼 보석을 움직여서 적을 공격하거나, 적의 공격을 방어하거나, 스킬을 사용하기 위한 마나를 모을 수 있다.

플레이어의 체력이 0이 되면 패배하고, 게임은 플레이어가 패배할 때까지 계속해서 새로운 적이 등장하는 방식으로 진행된다.

적의 체력을 0으로 만들면 승리하고, 플레이어는 약간의 체력 회복과 적의 스킬을 자신의 스킬로 가져올 수 있다.

------

개발 범위: 

3-매치 게임 기본 베이스

특정한 방법으로 매칭시킬 시 생성될 특수 블럭

플레이어가 사용 가능한 여러 종류의 스킬

플레이어가 상대할 여러 종류의 적

플레이어가 보상으로 얻을 아이템

전투 승리 후 경로 선택, 이벤트 등의 로그 라이크 요소

------

예상 게임 실행 흐름

![슬레이 더 스파이어, 이 '미완성' 게임이 '압도적 긍정적' 평가를 받은 비결은? | 1boon](http://t1.daumcdn.net/liveboard/thisisgame/ddd39fe455fa45fd9665d27259f887f0.jpg)

플레이어는 자신이 진행할 경로를 선택하고 적과 조우하게 된다.

![퍼즐 퀘스트 한글패치 (Puzzle Quest , 2007) : 네이버 블로그](https://lh3.googleusercontent.com/proxy/HpZNCz6EWcLTmt5FMISzHk3cU60SCDJlfKjOceieS1fbrsPihrCdICFFfkCbkR7NRjhtOCl6jO8gqH82NaVr6DKrxMXi62jc7qKVSoxLBJRTxTKa-NuqNo5Kg5liwvoREkmAbeB7pyrRJpHukWLN6_O0ss6gZG566KDptbjU-OtH2q2eLieO)

플레이어가 적과 조우하면 전투가 시작되고, 플레이어는 보석 매칭을 통해서 마나를 모으고 스킬을 사용하는 등의 방법으로 적을 쓰러뜨린다.

적을 쓰러뜨리면 보상을 얻고 다시 경로 선택으로 돌아간다.

![img](https://mblogthumb-phinf.pstatic.net/MjAxODAyMDVfMSAg/MDAxNTE3NzY2Mzk3NjYy.aIqDnwrBn05-5RVaUZwDYtqyQIybJ3213QtrOooa0TMg.TNxqR6CTadknbw1UxF16NRWqhkMZvygo1tuWvicw8dYg.JPEG.jeff_jks/20180205023932_1.jpg?type=w2)

플레이어가 패배하면 게임이 종료되고 점수를 기록한다.

------

| 주차  |                       내용                        |
| :---: | :-----------------------------------------------: |
| 1주차 |    메인 게임(3-매치 퍼즐)을 위한 레이아웃 제작    |
| 2주차 |           랜덤한 블럭 생성 및 이동 기능           |
| 3주차 |                 블럭의 매칭 기능                  |
| 4주차 | 메인 게임 흐름 제작(적의 공격을 포함한 전투 전체) |
| 5주차 |        플레이어가 사용할 수 있는 스킬 구현        |
| 6주차 |            플레이어가 상대할 적들 추가            |
| 7주차 |             보상으로 얻을 아이템 추가             |
| 8주차 |               게임 시작 / 종료 처리               |
| 9주차 |               디버깅 및 마무리 작업               |

