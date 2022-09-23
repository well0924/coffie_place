# coffiee place

# [소개]
  
  본 프로젝트는 제가 살고 있는 동네에 있는 카페를 확인해보고 
평점을 통해서 메인페이지에 top5를 보여주는 서비스입니다.

서비스를 이용하기 위해서는 로그인이 필수적이고 로그인없이
회원가입, 로그인,공지사항만 이용을 할 수 있습니다.
서비스를 이용하는 회원은 'USER'와 'ADMIN'으로 나뉩니다.
'USER'는 일반회원으로 서비스를 이용하기 위해서는 회원가입이 필요하고
로그인을 한 후에는 자유게시판과 카페검색을 할수 있습니다.
'ADMIN'은 관리자로 관라자 계정을 통해서 회원과 가게를 관리하는 
역할을 합니다.

# [목표]

혼자서 개발의 전 과정을 경험을 하는 것과 실시간으로 서버에 배포를
하는것에 목표를 두었습니다. 배포는 Cafe24의 Hosting을 구매를 해서
배포를 진행을 했습니다.

# [고도화 계획]

추가적으로 mybatis가 아닌 jpa로 컨버팅, CI/CD환경 구축 등의 계획을 갖고
있습니다.


### [제작기간] 
	
2022.04.13 ~ 2022.09.05
    
### [제작인원] 
    
1인
    
### [개발환경]

Frontend:HTML,CSS,JS,JQuery,theymleaf

Backend:Java,Spring Boot,Mybatis

DB:MariaDB

IDE&ETC: STS,Git,Swagger

Deployment:Cafe24

### [ERD]

<img src='https://user-images.githubusercontent.com/89343159/189042839-ff89b74b-a68a-4e8b-bd1e-de3fce33640f.PNG'></img>

### [WBS]

<img src='https://user-images.githubusercontent.com/89343159/189044341-974fe351-b7e3-4867-b8c9-ece09cfeaa81.png'></img>
 
### [주요기능] 

로그인

<img src='https://user-images.githubusercontent.com/89343159/189049604-f248376a-d32d-4371-9720-948e6ba114f2.png'></img>

회원가입

<img src='https://user-images.githubusercontent.com/89343159/189049695-92d7e32a-5137-4d83-a83b-f13ca1279f9a.png'></img>

ADMIN

<img src='https://user-images.githubusercontent.com/89343159/189052505-9e4c4ce4-a2fe-423f-bfed-81dedb3cedaa.png'></img>

USER

<img src='https://user-images.githubusercontent.com/89343159/189052390-0094cf8c-fdf1-4a73-9e90-7f7cdf34fcfe.png'></img>

카페 상세조회

<img src='https://user-images.githubusercontent.com/89343159/189052729-3d128727-6765-4929-a015-1cd507036acf.png'></img>

어드민 페이지

<img src='https://user-images.githubusercontent.com/89343159/189052917-203884fd-f518-4aab-b3f8-601c33a1cc8c.png'></img>

### 회고

처음 프로젝트 주제선정부터 배포까지의 전과정을 통해서 전반적인 개발
프로세스에 대한 이해도를 높일 수 있었던 프로젝트였습니다.특히 코드를 
구현하면서 수 많은 에러상황을 해결하기 위해서 고민하고 디버깅을 사용하
면서 해결했던 점이 기억에 많이 남았고 막연히 기능 구현에 의의를 두기 보다는
기능을 모듈화해서 코드의 생산성을 높이는데 노력을 했었습니다. 구현을 하는데
많은 시간이 걸렸지만 많은 경험을 할 수 있었던 프로젝트였고 나아가서 현업에서
도 실서비스를 구현 및 운영해보면서 부족한 부분을 채워나가고 나아가 대규모
트래픽을 처리할 수 있는 단계까지 나아가고 싶습니다.