# MyCameraCrop
# Kotlin

갤러리에서 사진선택하고, 크롭하여 이미지뷰에 뿌리기

**중요**
FLAG_GRANT_WRITE_URI_PERMISSION, READ_PERMISSION을 주어야 uri 활용하 작업에 지장이 없음
(PERMISSION 주지 않으면 저장할 수 없다는 토스트메시지가 뜬다)

**galleryAddPhoto()**
crop된 이미지를 갤러링 저장하는 함수.
(완벽하지 않은 코드_실제로 저장되는 것을 확인 못 함)

참고사이트
https://programmar.tistory.com/5
