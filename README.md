# DrunkenDiary
Activity
  앱에서 사용하는 패키지들을 포함하고 있습니다.
  SplashActivity: 앱 실행시 가장 먼저 표시되는 화면입니다.
    배경과 함께 로고를 보여준 뒤 1초 후에 MainActivity로 이동합니다.
  MainActivity: 앱의 기본 화면 틀입니다. 아래에 배치된 TabHost를 사용해 3개의 Fragment를 포함하고 있습니다.
  ItemActivity: 달력에서 날짜 클릭 시 나타나는 화면입니다. 기존에 등록한 정보를 보거나 새 음주 기록을 등록할 수 있습니다.
Fragment
  CalendarFragment: MainActivity의 첫 번째 Fragment입니다. 달력과 간단한 정보를 표시합니다.
  DiaryFragment: MainActivity의 두 번째 Fragment입니다. 음주 정보를 리스트 형식으로 보여줍니다.
  StatsFragment: MainActivity의 세 번째 Fragment입니다. 월별 음주 통계를 표시합니다.
  InfoFragment: StatsFragment 안에서 주종별 단위 정보를 표시합니다.
  
DB
  SQLite를 사용한 두개의 테이블을 가지고 있습니다.
  alcohol TABLE
    각 날짜별 마신 술의 종류와 양을 저장합니다.
    date, kind, bottle, glass 필드를 가집니다.
  diary TABLE
    각 날짜별 노트와 몸 상태를 기록합니다.
    date, condition, note 필드를 가집니다.
