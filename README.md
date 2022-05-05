# Rent-Company-Server
# 렌트 회사
자동차 렌트 서비스 서버
 
## 서비스 소개
자동차를 대여 해주는 웹 서비스

## 구현 사항

###Back end

**spring boot(REST API)
1. Spring Data JPA를 통한 DB 접근 및 관리
2. QueryDSL를 통해 쿼리를 메소드화하여 쿼리의 오류를 컴파일 시점에서 처리
3. Swagger를 사용하여 API 문서 자동화

To do..
1. 주 저장소로 AWS RDS(MySQL) 사용
2. AOP 활용
3. Spring Sercuirty와 JWT 방식을 사용하여 토큰을 통한 사용자 인증 구현