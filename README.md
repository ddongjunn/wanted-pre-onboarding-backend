# wanted-pre-onboarding-backend

### 요구사항 분석 및 구현 과정
**1. 채용공고를 등록합니다.**
```
POST /jobs
{
   "회사_id" : 회사_id,
   "채용포지션" : "백엔드 주니어 개발자",
   "채용보상금" : 1000000,
   "채용내용" : "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
   "사용기술" : "Python"
}
```

**2. 채용공고를 수정합니다.**
```
PATCH /jobs
{
   "채용공고_id" : 채용공고_id,
   "채용포지션" : "풀스택 개발자",
   "채용보상금" : 500000,
   "채용내용" : "원티드랩에서 풀스택 개발자를 채용합니다. 자격요건은..",
   "사용기술" : "java"
}
```
**3. 채용공고를 삭제합니다.**
```
DELETE /jobs/{채용공고_id}
```

**4. 채용공고 목록을 가져옵니다.**

```
GET /jobs
[
    {
        "채용공고_id": 1,
        "회사명": "배달의민족",
        "국가": "한국",
        "지역": "판교",
        "채용포지션": "백엔드 주니어 개발자",
        "채용보상금": 1000000,
        "사용기술": "java"
    },
    {
        "채용공고_id": 2,
        "회사명": "카카오",
        "국가": "한국",
        "지역": "판교",
        "채용포지션": "풀스택 개발자",
        "채용보상금": 500000,
        "사용기술": "Javascript"
    }
]
```

**5. 채용공고 검색 기능 구현**
```
GET /jobs?search={회사명, 채용포지션}
```
```
[
      {
         "채용공고_id": 채용공고_id,
	  "회사명":"원티드랩",
	  "국가":"한국",
	  "지역":"서울",
	  "채용포지션":"백엔드 주니어 개발자",
	  "채용보상금":1500000,
	  "사용기술":"Python"
	},
	{
         "채용공고_id": 채용공고_id,
	  "회사명":"네이버",
	  "국가":"한국",
	  "지역":"판교",
	  "채용포지션":"Django 백엔드 개발자",
	  "채용보상금":1000000,
	  "사용기술":"Django"
	}
]
```
**6. 채용 상세 페이지를 가져옵니다.**
```
GET /jobs/detail/{채용공고_id}
```
```
{
    "채용공고_id": 1,
    "회사명": "배달의민족",
    "국가": "한국",
    "지역": "판교",
    "채용포지션": "백엔드 주니어 개발자",
    "채용보상금": 1000000,
    "사용기술": "java",
    "회사가올린다른채용공고": [
        1,
        3
    ]
}
```
**7. 사용자는 채용공고에 지원합니다**
```
POST /jobs/apply
{
  "채용공고_id" : 2,
  "사용자_id" : 1
}
```
**8. 회사를 등록합니다.**
```
{
    "회사이름" : "카카오",
    "지역" : "판교",
    "국가" : "대한민국"
}
```
---
### 로그 
@LoggableController : 컨트롤러에 해당 어노테이션 사용시 request, parameter, response logging 처리


@Pointcut("@within(com.api.employment.common.logging.LoggableController)")
private void loggableControllers(){}
----
### 구현과정
JobPosting service에서 jobPostingReposioty.findById() 중복코드 발생으로 repository interface default 메소드 생성

```java
default JobPosting getById(Long id){
return this.findById(id).orElseThrow(() -> new CustomException(ErrorCode.JOB_POSTING_ID_NOT_FOUND));
}
```