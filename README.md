# coffiee place

# 순서

- 기능 및 구현화면

- ERD

- 화면UI

- 회고

# 1.프로젝트 소개

본 프로젝트는 제가 살고 있는 동네에 있는 카페를 확인해보고
평점을 통해서 메인페이지에 top5를 보여주는 서비스입니다.

서비스를 이용하기 위해서는 로그인이 필수적이고 로그인없이
회원가입, 로그인,공지사항만 이용을 할 수 있습니다.
서비스를 이용하는 회원은 'USER'와 'ADMIN'으로 나뉩니다.
'USER'는 일반회원으로 서비스를 이용하기 위해서는 회원가입이 필요하고
로그인을 한 후에는 자유게시판과 카페검색을 할수 있습니다.
'ADMIN'은 관리자로 관라자 계정을 통해서 회원과 가게를 관리하는
역할을 합니다.

### [목표]

본 프로젝트는 개발계획 서버에 프로젝트를 배포를 하는것에 목표를 두었습니다.

### [고도화 계획]

추가적으로 mybatis가 아닌 jpa로 컨버팅, CI/CD환경 구축 등의 계획을 갖고
있습니다.


### [제작기간]

1차:2022.04.13 ~ 2022.09.05

### [제작인원]

1인

# 2.개발 환경

### [Back-end]

Java, Springboot , Mybatis, SpringSecurity


### [Front-end]

Html, Css, JQuery, Thyemleaf


### [DB]

MariaDB

### [ETC]

git, swagger


### [Depoly]

Cafe24

# 3.기능 및 구현화면

### 1. 메인화면

![localhost_8087_page_main_mainpage](https://user-images.githubusercontent.com/89343159/193506616-3f9e16d1-bdd2-4d9a-a59c-bd79258e1462.png)

       1. 로그인 링크를 누르면 로그인 화면으로 이동을 하며 해당 화면에서 로그인을 하거나 회원가입 및 아이디 및 비밀번호 찾기 페이지로 이동 할 수 
          있습니다. 
       
       2. 회원가입링크를 누르면 회원가입 페이지로 이동을 해서 회원가입을 할 수 있습니다.

       3. 가게 평점에서 점수가 높은 가게 5곳을 이미지 슬라이더로 가게이름 과 주소 평점을 볼수 있습니다.

       4. 가게검색 및 자유게시판, 공지게시판 페이지로 가서 목록을 확인을 할 수 있다.(게시글 열람 및 가게 조회는 로그인을 한 후에 가능합니다.) 

### 2. 로그인 페이지

### 2-1.로그인 화면
![로그인화면](https://user-images.githubusercontent.com/89343159/193510701-784d63ca-8c2f-4d17-ba8a-3f5173882f21.png)

       1. 로그인 버튼을 누르면 인증절차를 거쳐서 관리자면 [ROLE_ADMIN] 으로 표시 유저면 [ROLE_USER]로 표시합니다.
       
       -회원         

![로그인(회원)](https://user-images.githubusercontent.com/89343159/193513264-19b1948e-fc22-4134-b551-01ff388052f6.PNG)

       -어드민

![로그인(어드민)](https://user-images.githubusercontent.com/89343159/193513258-62e25a0d-75c6-4eb2-8a56-923121107242.PNG)

       1-1. 아이디 및 비밀번호를 입력하지 않고 로그인을 누를 경우

![실패1](https://user-images.githubusercontent.com/89343159/193510910-beb9a105-ecc1-42f8-ab4f-e15984152a8b.PNG)

       1-2. 비밀번호 및 아이디가 맞지않은 경우

![실패2](https://user-images.githubusercontent.com/89343159/193510914-850f31ef-2970-4d4f-bb31-a0becfb9b95a.PNG)

### 2-2.아이디 및 비밀번호 찾기

![아이디찾기 및 비밀번호 찾기](https://user-images.githubusercontent.com/89343159/193511399-4056c6ac-0ef3-4808-8740-b716c262a792.png)

      2-2-1. 아이디 찾기는 회원의 이름과 이메일을 작성을 하면 화면 밑에 회원의 아이디를 출력합니다.
      
      -성공시 

![아이디찾기성공](https://user-images.githubusercontent.com/89343159/193511710-72adbe17-5148-4c95-b842-ff5a002fc793.PNG)

      -실패시

![아이디찾기 실패](https://user-images.githubusercontent.com/89343159/193511700-cf8d4166-b0f2-44bc-8402-7a0c74b68c78.PNG)

      2-2-2. 비밀번호 찾기는 회원아이디와 이메일을 입력을 하면 비밀번호 재설정 페이지로 이동을 합니다. 
      
      2-2-3. 비밀번호 재설정 페이지에서는 아이디와 새로운 비밀번호를 입력을 하고 비밀번호가 일치여부를 확인후 비밀번호를 재설정합니다.

![비밀번호 재설정페이지](https://user-images.githubusercontent.com/89343159/193512090-0941e3ce-0d1d-46ec-8cf7-08c9d60b6dac.PNG)


### 3. 회원가입 페이지

![회원가입(1)](https://user-images.githubusercontent.com/89343159/193512460-214392d4-6072-45ed-a69e-293f1377180f.png)

     3-1-1.아이디 중복여부로 버튼을 누르면 중복여부를 출력합니다.

     - 실패시 

![회원가입(아이디중복)](https://user-images.githubusercontent.com/89343159/193512597-4efcef04-a071-4039-9b18-56382955552c.png)

     3-1-2. 비밀번호를 입력을하면서 비밀번호가 일치를 하는지 확인을 합니다.

     -실패시

![회원가입(비밀번호)](https://user-images.githubusercontent.com/89343159/193512589-6f801f81-b528-4d3f-b0e4-7d8e6b8f78c4.png)

     3-1-3. 주소입력 버튼을 누르면 Daum post api를 이용해서 주소지를 입력을 할 수 있습니다.

     3-1-4. 회원가입버튼을 누르면 비밀번호를 암호화하고 입력칸에 빈칸이 있으면 유효성 검사를 하고 제대로 입력을 하면 회원가입을 합니다.

### 4. 자유게시판

### 4-1. 자유게시판 목록 페이지

![자유게시판 목록](https://user-images.githubusercontent.com/89343159/193513851-b5137fae-eaa4-4c1d-aee7-856b99d6c30e.png)

     4-1-1. 글작성 버튼을 누르면 글작성 페이지로 이동을 합니다. 로그인을 하지 않으면 버튼은 활성화 하지 않습니다.

     4-1-2. 선택창에서는 제목,작성자,내용,작성자+내용으로 선택을 해서 게시물을 검색을 할 수 있습니다.

     4-1-3. 글 제목을 클릭을 하면 해당 글을 조회를 할 수 있습니다.

### 4-2. 자유게시판 글 작성 페이지

![자유게시판 글작성](https://user-images.githubusercontent.com/89343159/193515771-5976e142-e543-4a7e-9ad3-74a4afe935ac.png)

     4-2-1. 게시글을 작성하면서 파일첨부를 할 수 있습니다.(다중첨부)    

     4-2-2. 목록버튼을 누르면 게시글 목록으로 이동을 합니다.

     4-2-3. 게시글 작성을 누르면 게시글 작성기능을 하고 완료된 뒤에는 게시글 목록 페이지로 이동하고 게시글에 작성되지 않은 부분은
            유효성 검사를 실행합니다. 

     4-2-4. summernote에디터를 적용을 해서 이미지를 업로드 할 수 있고 글꼴 변형,링크첨부 등 다양한 기능을 할 수 있습니다. 

### 4-3. 자유게시판 조회 페이지

![localhost_8087_page_board_detail_5017](https://user-images.githubusercontent.com/89343159/193516131-2225bbb5-e425-468f-9ac7-14383e00892b.png)

     4-3-1. 게시글 작성시 파일첨부를 했으면 해당 글을 클릭하면 파일을 다운로드 할 수 있습니다.

     4-3-2. 수정버튼을 누르면 글작성시 비밀번호를 입력을 했으면 비밀번호 입력페이지로 이동을 하고 입력을 하지 않았으면
            
            수정페이지로 이동을 합니다.
     
     4-3-3. 목록버튼을 누르면 게시글목록 으로 이동을 합니다.

     4-3-4. 댓글 작성버튼을 누르면 댓글 내용을 입력하지 않으면 유효성검사를 실행을 하고 작성을 하면 댓글을 작성합니다.

     4-3-5. 댓글 삭제버튼을 누르면 댓글삭제내용이 나오고 댓글이 삭제 됩니다.

### 4-4. 자유게시판 비밀번호 입력

![자유게시판 비밀번호 입력](https://user-images.githubusercontent.com/89343159/193516151-22620af3-0873-46f9-afbc-da904a781c39.png)

     글작성시 비밀번호를 입력을 했을 경우 해당 비밀번호를 입력을 하면 게시글 수정페이지로 이동을 합니다.

### 4-5. 자유게시판 수정 페이지

![자유게시글 수정](https://user-images.githubusercontent.com/89343159/193514146-8f045caf-6cf8-418c-b2b6-1ddbbd8cc935.png)

    4-5-1. 목록버튼을 누르면 게시글 목록으로 이동합니다.

    4-5-2. 게시글 수정버튼을 누르면 게시글 및 파일첨부를 수정을 할 수 있습니다.
    
    4-5-3. 게시글 삭제버튼을 누르면 게시글을 삭제합니다.

### 5. 공지 게시판

### 5-1. 공지게시글 목록
![공지게시판](https://user-images.githubusercontent.com/89343159/193526065-92768975-a313-4e66-afc4-c572f9e703af.png)

    5-1-1. 글작성 버튼을 누르면 공지게시판 작성페이지로 이동을 합니다. 어드민 계정이 아니면 버튼은 활성화 되지 않습니다.

    5-1-2. 게시글 선택에 공지카테고리별, 작성자, 내용 별로 게시글을 검색 할 수 있습니다.

    5-1-3. 게시글 제목을 클릭을 하면 공지게시글 조회 화면으로 이동을 합니다.(글 열람은 로그인을 해야 열람을 할 수 있습니다.)

### 5-2. 공지게시글 작성

![공지게시글작성](https://user-images.githubusercontent.com/89343159/193529080-96b8f96f-8b46-4036-aed8-0a6f3f80e2e7.png)

    5-2-1. 글작성 버튼을 누르면 공지게시글을 작성하고 공지게시글 목록으로 이동합니다. 게시글을 작성하지 않고 누르면 유효성 검사를 합니다.

    5-2-2. 목록 버튼을 누르면 공지게시글 목록으로 이동을 합니다.

    5-2-3. 글작성 내용에서 에디터를 이용할 수 있습니다. 

### 5-3. 공지게시글 조회

![공지게시글 열람](https://user-images.githubusercontent.com/89343159/193526089-8c2d7fd6-b6da-4c13-8625-8277b03bac06.png)

    5-3-1. 글작성시 파일첨부를 했으면 파일을 다운로드를 할 수 있습니다.

    5-3-2. 수정하기 버튼을 누르면 공지게시글 수정페이지로 이동을 해서 글 수정 및 글 삭제를 할 수 있습니다.

    5-3-3. 목록버튼을 누르면 공지게시글목록으로 이동합니다.

### 5-4. 공지게시글 수정

![공지게시글수정](https://user-images.githubusercontent.com/89343159/193526098-2ff5e729-ffe2-449f-a75f-58d9c88cfc23.png)

    5-4-1. 삭제버튼을 누르면 게시글을 삭제하고 공지게시글 목록 페이지로 이동합니다.

    5-4-2. 수정버튼을 누르면 게시글의 내용을 수정하고 공지게시글 목록 페이지로  이동합니다.

    5-4-3. 목록버튼을 누르면 공지게시글목록으로 이동 합니다.

### 6. 카페 검색

### 6-1. 가게 목록페이지

![가게목록](https://user-images.githubusercontent.com/89343159/193530252-55788d59-47ef-4c64-a062-a4e379f65f6b.png)

    6-1-1. 가게 이름 및 가게주소를 선택해서 해당 가게를 검색할 수 있습니다.

    6-1-2. 가게 목록을 엑셀 파일로 다운로드를 할 수 있습니다.(어드민계정으로 로그인을 했을시 입니다.)

    6-1-3. 가게등록 버튼을 누르면 가게등록 페이지로 이동을 합니다.(어드민 계정으로 로그인을 했을 경우입니다.)

![가게조회(1)](https://user-images.githubusercontent.com/89343159/193530262-8d57809b-9734-457f-a99b-8be66de040e1.png)

    6-2-1. 가게조회를 했을시 kakao map을 활용해서 가게의 위경도를 넣어서 지도의 위치를 보여줍니다.

    6-2-2. 가게메인이미지를 보여주고 이미지를 클릭을 하면 팝업창으로 해당 이미지를 볼 수 있습니다. 작은 이미지 또한 같습니다.

    6-2-3. 카페찜하기 버튼을 누르면 완료버튼이 나오고 마이페이지에 위시리스트 목록에 추가됩니다. 한번 추가를 한뒤 누르면 중복체크를 합니다.

    6-2-4. 가게수정버튼을 누르면 가게 수정페이지로 이동을 합니다.(어드민계정으로 로그인을 했을 시 입니다.)

    6-2-5. 목록버튼을 누르면 가게 목록화면으로 이동을 합니다.

    6-2-6,7. 가게 댓글을 작성시 가게 평점 및 댓글 내용을 입력을 해서 댓글을 작성합니다. 평점을 입력을 하면 가게 목록 페이지의 평점에 반영이 됩니다.
           댓글 내용을 작성하지 않으면 유효성검사를 실행합니다.

    6-2-8. 댓글에서 하트버튼을 클릭을 하면 추천기능을 할 수 있습니다. 누른뒤 다시 누르면 좋아요 수가 1줄어듭니다.

    6-2-9. 댓글 삭제버튼을 누르면 댓글을 삭제합니다.

### 7. 마이 페이지

### 7-1. 마이페이지 화면

![마이페이지(회원)](https://user-images.githubusercontent.com/89343159/193532620-650f5d9d-174e-4ded-8f9b-37e7b7dd7d68.png)

    7-1-1.가게 조회 페이지에서 카페찜하기 버튼을 누른 가게의 정보를 카드형식으로 가게정보와 평점을 볼 수 있습니다.

    7-1-2.회원정보 수정링크를 클릭하면 회원정보수정 페이지로 이동을 할 수 있습니다.(어드민 계정으로 로그인을 하면 활성화 되지 않습니다.)

    7-1-3.로그인한 회원의 글(자유게시판,공지게시판)을 확인을 할 수 있다.

![내가작성한 글](https://user-images.githubusercontent.com/89343159/193532628-ce4a1f8b-fb2b-44cd-b6e5-ce3d0e94c502.png)

    7-1-4.회원탈퇴링크를 누르면 회원을 탈퇴합니다.(어드민 계정으로 보면 해당 부분은 활성화 되지 않습니다.)

    7-1-5.상세버튼을 누르면 가게의 상세조회 페이지로 이동합니다.

    7-1-6.위시리스트 삭제버튼을 누르면 위시리스트를 삭제를 합니다.

### 8. 어드민 페이지

### 8-1.어드민 목록 페이지

![어드민페이지1](https://user-images.githubusercontent.com/89343159/193540079-92d4b28d-a8a5-4b23-9ca8-2a2da3ba4845.png)

    8-1-1.회원 아이디를 클릭하면 회원 정보를 볼수 있고 수정할 수 있습니다.
     
    8-1-2.검색 필터에 아이디, 이름, 이메일 별로 회원을 검색을 할 수있고, 아이디를 입력을 하면 자동으로 키워드가 나오게 했습니다.

    8-1-3.가게목록을 누르면 가게 목록 페이지로 이동합니다.

    8-1-4.가게등록 버튼을 누르면 가게등록 페이지로 이동을 합니다.

    8-1-5.선택삭제는 목록에서 삭제하고자하는 회원을 체크박스에 선택을 해서 원하는 만큼 회원을 삭제할 수 있습니다.
   
    8-1-6.공지글 작성버튼은 공지게시판 글 작성 페이지로 이동을 할 수 있습니다.

### 8-2.가게 등록 페이지

![가게등록페이지](https://user-images.githubusercontent.com/89343159/193579531-83febf6f-6376-42c2-89d8-6e02a3787d26.png)

    8-2-1. 가게 등록 페이지에서는 가게정보를 적고 가게 이미지를 첨부를 해서 가게등록을 합니다. 작성이 되지 않은 부분은 유효성 검사를 실행합니다.

### 8-3.가게 수정 페이지

![가게수정](https://user-images.githubusercontent.com/89343159/193579540-4b356c2e-3c4b-4e81-8c9f-63cd3178a35e.png)

    8-3-1. 가게 수정 페이지에는 가게정보와 지도위치를 보여주고 이미지를 첨부를 해서 가게정보를 수정할 수 있습니다.

# 4.ERD

![](https://user-images.githubusercontent.com/89343159/191969692-adf3e435-d095-44dc-8eb2-79f79667a2d6.PNG)

# 5.프로젝트 중 관련 이슈 및 구현 기술

### 1. Swagger를 활용해서 Rest Api 문서 자동화

### 1-1.Swagger를 사용하려면 먼저 gradle에 라이브러리를 주입


<code>
    // Swagger
    implementation 'io.springfox:springfox-boot-starter:3.0.0'
    implementation 'io.springfox:springfox-swagger-ui:3.0.0'
</code>

### 1-2.Swagger에 관련된 설정 Class를 작성

<code>

@Configuration // 설정을 위한 어노테이션
@EnableSwagger2 // 스웨거를 사용하기 위한 어노테이션
@ComponentScan(basePackages = {"com.kr.coffie.*"}) // 해당 위치를 기반으로 스웨거 어노테이션이 있는 컨트롤러를 스캔
public class SwaggerConfig {
//
private ApiInfo apiInfo() {
return new ApiInfoBuilder()
.title("CoffiePlace")
.version("version 0.2")
.description("Coffieplace Api")
.license("license")
.build();
}

    //swagger설정
    @Bean
    public Docket swaggerApi() {
       return new Docket(DocumentationType.SWAGGER_2)
        	.useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kr.coffie"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo());
    }
}
</code>

위의 코드를 토대로 해서 설명을 하면 Docket은 Swagger의 설정을 도와주는 class를 말합니다.

useDefaultResponseMessages :

기본적으로 swagger-ui로 들어가서 api의 기본적인 응답값을 만들어줍니다.

해당 기능을 false로 해놓으면 응답값을 커스텀 할 수 있습니다.

select :

ApiSelectorBuilder 클래스의 인스턴스를 반환합니다.

해당 인스턴스를 통해 Swagger의 end-point를 제어할 수 있습니다.

apis :

api spec이 작성되어 있는 패키지를 지정합니다.

RequestHandlerSelectors.any()로 설정한다면 전체 Api에 대한 문서를 나타낼 수 있습니다.

paths :

path의 조건에 해당하는 Api를 문서화합니다.

PathSelectors.any()로 한다면 전체 Api 패턴에 대한 문서를 나타낼 수 있습니다.

PathSelectors.ant()로 특정 Api url pattern을 가진 Api만 문서를 만들 수도 있습니다.

apiInfo :

아래의 Swagger Api 문서에 대한 설정 객체를 등록해줍니다.

ApiInfo는 Swagger API 페이지에 대한 내용을 담고 있습니다.

title : API 문서의 제목

description : API 페이지에 대한 설명

version : API 문서의 버전

### 1-3. 스웨거 어노테이션 사용


<code>
 @Api(tags = {"자유게시판 Api"} ,value="자유게시판에 사용되는 기능 api")
 @RestController
 @AllArgsConstructor
 @RequestMapping("/api/board/*")
 public class BoardApiController {
....
</code>

@Api : Api가 어떤 역할을 하는 지 표시하는 어노테이션으로 컨트롤러 위에 추가를 합니다.

- tags : Swagger UI에 보일 컨트롤러의 Title명칭 부여

- value : 컨트롤러 옆에 보일 간단한 정보

<code>

	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "게시글 전체 조회 API",notes="자유게시판에서 글목록을 조회합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="keyword",value="검색어",example="test",dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page",value="페이지",example="1",dataType = "Integer",paramType = "query"),
		@ApiImplicitParam(name="perPageNum",value="페이지번호",example="5",dataType = "Integer",paramType = "query"),
		@ApiImplicitParam(name="searchType",value="검색타입",example="T",dataType = "String",paramType = "query")
	})
	@GetMapping(value="/list")
	public ResponseDto<List<BoardDto.BoardResponseDto>> articelist(Criteria cri)throws Exception{
		
		List<BoardDto.BoardResponseDto>list = null;
		
		int totallist =0;
		
		list = service.boardlist(cri);
		totallist = service.totalarticle(cri);
		
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(totallist);
		
		return new ResponseDto<>(HttpStatus.OK.value(),list);
	}

</code>

@ApiOperation : method에 대한 설명과 기능을 표시하는 어노테이션

- value : API에 대한 정보를 요약해서 설명

- notes : API에 대한 정보를 자세히 설명

@ApiResponses : api의 응답값을 모아놓은 어노테이션

@ApiResponse : api의 응답값에 맞게 response의 설명을 작성하는 어노테이션

@ApiImplicitParam : 해당 API Method 호출에 필요한 Parameter들의 설명을 추가할 수 있다.

@ApiIgnore : Swagger ui에 표시를 하지 않게 하는 어노테이션


<code>
	@ApiModel(value="게시판 요청 dto",description = "자유게시판 요청에 필요한 dto")
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class BoardRequestDto {

		@ApiModelProperty(value="게시글번호",example="1")
		private Integer boardId;
		
		@ApiModelProperty(value="게시글제목",example="title",required = true)
		@NotBlank(message = "제목을 입력해주세요.")
		private String boardTitle;
		
		@ApiModelProperty(value="게시글내용",example="contents",required = true)
		@NotBlank(message = "내용을 입력해주세요.")
		private String boardContents;
		
		@ApiModelProperty(value="게시글저자",example="writer",required = true)
		private String boardAuthor;
		
		@ApiModelProperty(value="게시글 조회수",example="0",required = true)
		private Integer readCount;
		
		@ApiModelProperty(value="게시글비밀번호",example="1111")
		private Integer passWd;
		
		@ApiModelProperty(value="게시글 파일그룹아이디",example="free_ge3b53",required = true)
		private String fileGroupId;
		
		@ApiModelProperty(value="글 등록일",example="2022-09-21 12:34:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
		private LocalDateTime createdAt;
	
	}
</code>

@ApiModel :

- value:  모델에 대한 설명
- description: 모델에 대한 상세 설명

@ApiModelProperty :

- value:  속성에 대한 설명
- example: 속성의 default 값 또는 예시
- position: Swagger 문서에서 보이는 순서
- required: 속성의 필수여부 표기. 필수는 true, 아니면 false.


### 2.ImageScaling 을 활용해서 이미지 업로드시 리사이징으로 서버용량 최적화

- 2-1.상황

- 가게 등록 페이지에서 가게 이미지를 업로드할 때 이미지의 크기가 일정하지 않다는 점

- 가게 이미지의 크기가 크면 용량도 크므로 용량을 줄일 필요가 있다고 판단

- 2-2.적용

- 이미지를 리사이징을 하는데 ImageScaling라이브러리를 활용해서 업로드를 하는 이미지를 리사이징을 하는 기능을 구현

<code>
	//ImageScaling
	implementation 'com.jhlabs:filters:2.0.235-1'
	implementation 'com.mortennobel:java-image-scaling:0.8.6'	
</code> 

<code>
//이미지 리사이징
		public String ResizeFile(FileDto.ImageResponseDto dto,int width,int height) {

			String defaultFolder = filePath+File.separator+dto.getImgGroup()+File.separator+dto.getFileType()+File.separator;

			String originFilePath = defaultFolder+dto.getStoredName();
			
			File file = new File(originFilePath);
			
			String thumblocalPath = "";
			
			boolean resultCode = false;
			
			try {

				if(filePath != null && filePath.length() !=0) {
					
					String originFileName = file.getName();
				
					String ext = originFileName.substring(originFileName.lastIndexOf(".")+1);
					
					String thumbFileName = originFileName.substring(0,originFileName.lastIndexOf("."))+"_thumb."+ext;
					
					BufferedImage originImage = ImageIO.read(new FileInputStream(file));
					
					MultiStepRescaleOp scaleImage = new MultiStepRescaleOp(width,height);
					
					scaleImage.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);
					
					BufferedImage resizeImage = scaleImage.filter(originImage,null);
					
					String fullPath = defaultFolder + "thumb"+File.separator+ thumbFileName;
					
					File out = new File(fullPath);
					
					if(!out.getParentFile().exists()) {

						out.getParentFile().mkdirs();
					
					}
					
					if(!out.exists()) {
						
						resultCode = ImageIO.write(resizeImage, ext, out);
						
						if(resultCode) {

							thumblocalPath = imgPath + dto.getImgGroup()+"/"+dto.getFileType()+"/thumb/"+out.getName();
							
						}else {
							thumblocalPath = null;

						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return thumblocalPath;
		}
</code>

서비스 단에서 이미지의 크기를 조절해서 리사이징을 하면 된다.
<code>
public int placeregister(PlaceDto.PlaceRequestDto dto,FileDto.ImageRequestDto imgvo)throws Exception{
int result = mapper.placeregister(dto);

		List<FileDto.ImageResponseDto>imagelist = new ArrayList<>();
		FileDto.ImageResponseDto imglist = null;
		
		//이미지 업로드
		if(result > 0) {
			imagelist = utile.imagefileupload(imgvo);
		}
		
		//첨부 파일이 있는 경우
		if(imagelist != null) {
			String resize = "";
			
			for(int i =0 ; i<imagelist.size();i++) {
				
				imglist = imagelist.get(i);
				//첫번째 이미지의 경우
				if(i == 0) {
					imglist.setIsTitle("1");
                                        //이미지 리사이징
					resize = utile.ResizeFile(imglist, 360, 360);
				}else {//나머지
					resize = utile.ResizeFile(imglist, 120, 120);
				}
				
				imglist.setPlaceId(dto.getPlaceId());
				imgvo.setImgGroup("coffieplace");
				imgvo.setFileType("place");
				imglist.setThumbFileImagePath(resize);
				
				int attachresult = filemapper.placeimageinsert(imglist);
				
			}
		}
						
		return result;
	};
</code>

### 3. Apache Poi를 활용해서 엑셀 파일 다운로드 기능

- 3-1. 상황

- Apache poi를 활용해서 가게 목록을 엑셀 파일로 다운로드를 할 수 있는 기능을 완성했지만 좀 더 유지보수를 위해서 코드 자체를 리팩토링을 하기로 했습니다.

- 3-2. 해결

컨트롤러
<code>
@Operation(summary = "가게 목록 엑셀 다운로드",description = "가게 목록을 엑셀파일로 다운로드한다.",responses = {
@ApiResponse(responseCode = "204")
})
@GetMapping("/place-download")
public DownloadResponseDto<?> getPlaceListDownload(HttpServletRequest req, HttpServletResponse res) throws Exception {
List<Place>list = placeRepository.findAll();
List<PlaceResponseDto>result = list.stream().map(place->new PlaceResponseDto(place)).collect(Collectors.toList());
ExcelService<PlaceResponseDto>excelList = new ExcelService<>(result,PlaceResponseDto.class);
excelList.downloadExcel(res);
return new DownloadResponseDto<>();
}
</code>

<code>
@Getter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DownloadResponseDto<T> {
    private Integer status;
    private HttpHeaders headers;
    private Resource res;
}
</code>

<code>
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

	String headerName() default "";

}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelFileName {

	String fileName() default "";

}


@Getter
@RequiredArgsConstructor
public class ExcelRenderResource {


	private final String excelFileName;
	
	private final Map<String,String> excelHeaderNames;
	
	private final List<String> dataFieldNames;
	
	public String getExcelHeaderName(String dataFieldName) {
	
		return excelHeaderNames.get(dataFieldName);
	
	}
}


public class ExcelRenderResourceFactory {

	public static ExcelRenderResource preparRenderResource(Class<?>type) {
		
		String fileName = getFileName(type);
		
		Map<String,String> headerNamesMap = new LinkedHashMap<>();
		
		List<String>fieldNames = new ArrayList<>();
		
		for(Field field : SuperClassReflectionUtil.getAllFields(type)) {
			if(field.isAnnotationPresent(ExcelColumn.class)) {
				
				ExcelColumn annotation  = field.getAnnotation(ExcelColumn.class);
				
				fieldNames.add(field.getName());
				
				String headerName = annotation.headerName();
				
				headerName = StringUtils.hasText(headerName) ? headerName : field.getName();
				
				headerNamesMap.put(field.getName(), headerName);
			}
		}
		return new ExcelRenderResource(fileName, headerNamesMap, fieldNames);
	}
	
	private static String getFileName(Class<?>type) {
		
		String fileName = type.getSimpleName();
		
		if(type.isAnnotationPresent(ExcelFileName.class)) {
			
			fileName = type.getAnnotation(ExcelFileName.class).fileName();
			
			if(!StringUtils.hasText(fileName))fileName = type.getSimpleName();
		}
		return fileName;
	}
}


public class ExcelService<T> {

	private final Workbook workbook;
	
	private final Sheet sheet;
	
	private final ExcelRenderResource resource;
	
	private final List<T> dataList;
	
	private int rowIndex =0;
	
	public ExcelService(List<T>dataList,Class<T>type){
		
		this.workbook = new HSSFWorkbook();
		this.sheet =workbook.createSheet();
		this.resource = ExcelRenderResourceFactory.preparRenderResource(type);
		this.dataList = dataList;
	}
	
	public void downloadExcel(HttpServletResponse response) throws Exception {
	
		createHead();
		createBody();
		writeExcel(response);
	
	}
	
	private void createHead() {
	
		Row row = sheet.createRow(rowIndex++);
	
		int columnIndex = 0;
		
		for(String dataFieldName : resource.getDataFieldNames()) {
			
			Cell cell = row.createCell(columnIndex++);
			
			String value = resource.getExcelHeaderName(dataFieldName);
			
			cell.setCellValue(value);
		}
	}
	
	private void createBody()throws Exception{
	
		for(T data: dataList) {
			
			Row row = sheet.createRow(rowIndex++);
			
			int columnIndex = 0;
			
			for(String dataFieldName : resource.getDataFieldNames()) {
				
				Cell cell = row.createCell(columnIndex++);
				
				Field field = SuperClassReflectionUtil.getField(data.getClass(), (dataFieldName));
				
				field.setAccessible(true);
				
				Object cellValue = field.get(data);
				
				field.setAccessible(false);
				
				setCellValue(cell,cellValue);
			}
		}
	}
	
	private void writeExcel(HttpServletResponse response)throws Exception{

		String fileName = new String(resource.getExcelFileName().getBytes(StandardCharsets.UTF_8),StandardCharsets.ISO_8859_1);
		
		response.setContentType("application/vnd.ms-excel");
		
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,String.format("attachment; filename=\"%s.xls\"",fileName));
		
		workbook.write(response.getOutputStream());
		
		workbook.close();
	}
	
	private void setCellValue(Cell cell, Object cellValue) {
	
		if(cellValue instanceof Number) {
		
			Number numberValue = (Number)cellValue;
			
			cell.setCellValue(numberValue.doubleValue());
			
			return;
		}
		
		cell.setCellValue(ObjectUtils.isEmpty(cellValue)? "": String.valueOf(cellValue));
	
	}
}


@NoArgsConstructor
public class SuperClassReflectionUtil {

	public static List<Field> getAllFields(Class<?>clazz){
		List<Field> fields = new ArrayList<>();
		
		for(Class<?>clazzInClasses : getAllClassesIncludingSuperClasses(clazz,true)) {
			fields.addAll(Arrays.asList(clazzInClasses.getDeclaredFields()));
		}
		
		return fields;
	}
	
	public static Annotation getAnnotation(Class<?>clazz,Class<? extends Annotation>targetAnnotation) {
		
		for(Class<?>clazzInClasses : getAllClassesIncludingSuperClasses(clazz, false)) {
			
			if(clazzInClasses.isAnnotationPresent(targetAnnotation)) {
				return clazzInClasses.getAnnotation(targetAnnotation);
			}
		}
		return null;
	}
	
	public static Field getField(Class<?>clazz,String name)throws Exception{
		
		for(Class<?>clazzInClasses : getAllClassesIncludingSuperClasses(clazz,true)) {
			
			for(Field field : clazzInClasses.getDeclaredFields()) {
				
				if(field.getName().equals(name)) {
					return clazzInClasses.getDeclaredField(name);
				}
			}
		}
	 throw new Exception();
	}
	
	private static List<Class<?>> getAllClassesIncludingSuperClasses(Class<?>clazz,boolean fromSuper){
		List<Class<?>> classes = new ArrayList<>();
		
		while(clazz != null) {
			classes.add(clazz);
			clazz = clazz.getSuperclass();
		}
		if(fromSuper)Collections.reverse(classes);
		return classes;
	}
}
</code>

# 6.화면 UI

### [화면 UI]

- 카카오 오븐을 활용해서 만든 ui입니다.

- 오븐 주소

  [카카오오븐ui](https://ovenapp.io/view/EjO0ryUbmm7ObYlqlsKXqREeWQmZQH3f/msb3n)


### [개별 화면]

1. 메인 페이지

![01_메인페이지](https://user-images.githubusercontent.com/89343159/192147962-600cbf1b-0a5d-4197-98a3-708a388f5c83.png)

2.카페 검색 페이지

![02_카페검색페이지](https://user-images.githubusercontent.com/89343159/192147966-259a9ad1-e48b-4e85-a189-fcb91f92c699.png)

3.카페검색 상세조회 페이지

![03_검색페이지 상세조회](https://user-images.githubusercontent.com/89343159/192147978-0667c1b6-663c-47f2-a621-0a160e8a1fa3.png)

4.공지사항 목록 페이지

![04_공지사항 게시글 목록](https://user-images.githubusercontent.com/89343159/192147982-372f932e-30aa-4ba5-9ef7-b8dd6ecef5b0.png)

5.공지게시글 조회 페이지

![05_공지게시글 조회](https://user-images.githubusercontent.com/89343159/192148586-704b7ea8-ade7-4726-8dec-ed9d05507d54.png)

6.공지게시글 작성 페이지

![06_공지게시글 작성](https://user-images.githubusercontent.com/89343159/192148147-757605c6-c8ac-496e-8e49-a45769f51b35.png)

7.공지게시글 수정 페이지

![07_공지게시글 수정](https://user-images.githubusercontent.com/89343159/192148618-28033ab6-f0a6-4517-97e9-e92eb48fd491.png)

8.자유게시글 목록 페이지

![08_자유게시글 목록](https://user-images.githubusercontent.com/89343159/192148017-e86e90b5-9558-4537-be21-e1985fbe5473.png)

9.비밀번호 확인 페이지

![09_비밀번호 확인 페이지](https://user-images.githubusercontent.com/89343159/192148021-3476f291-d786-401c-b16f-4bc8245dc976.png)

10.자유게시글 목록 페이지

![10_자유게시글 조회](https://user-images.githubusercontent.com/89343159/192148023-0d8d0e4b-8532-4fe8-8a8d-ec01ef39bdf1.png)

11.자유게시글 작성 페이지

![11_자유게시글 작성](https://user-images.githubusercontent.com/89343159/192148029-06b9e777-3228-4fe0-a18c-cb26c82ad235.png)

12.자유게시글 수정 페이지

![12_자유게시글 수정](https://user-images.githubusercontent.com/89343159/192148490-537e2afe-0363-4e4a-a125-f85de524775b.png)


13.로그인 페이지

![13_로그인 페이지](https://user-images.githubusercontent.com/89343159/192148052-2cf2d49c-66b6-42b1-87b2-dd6e831a9817.png)

14.회원 가입 페이지

![14_회원가입페이지](https://user-images.githubusercontent.com/89343159/192148053-5c19691e-4ffc-4c74-bb52-ef531196d8fb.png)

15.관리자 페이지

![15_관리자 페이지](https://user-images.githubusercontent.com/89343159/192148054-8eb2e025-0a1e-4a09-a870-df50852b5a3c.png)

16.가게 등록 페이지

![16_가게등록페이지](https://user-images.githubusercontent.com/89343159/192148055-75895913-b4e3-45ae-a279-2f4e371cb20f.png)

17.가게수정 페이지

![17_가게수정페이지](https://user-images.githubusercontent.com/89343159/192148056-9784b0f9-4fde-466f-b2b0-05497ddab00c.png)

19.내가 작성한 글 페이지

![19_내가 작성한 글](https://user-images.githubusercontent.com/89343159/192148048-87df1e82-c625-44b7-8747-7d4d1843b911.png)

20. 아이디 비밀번호확인 페이지

![20_아이디 amp;비밀번호확인 페이지](https://user-images.githubusercontent.com/89343159/192148049-7caf57d2-69e2-4af0-b34a-4991c7d9eda1.png)

21.비밀번호 수정 페이지

![21_비밀번호 수정페이지](https://user-images.githubusercontent.com/89343159/192148050-feb0a6b5-9dde-471f-8bbe-75f08dbe0a41.png)

# 7.회고

### 1. vol.01 회고

첫 프로젝트를 하면서 주제선정부터 프로젝트 배포까지의 전과정을 거치면서 전반적인 개발의 흐름을 알수 있는 기회였던
프로젝트였습니다. 전반적으로 스프링의 기본적인 기능 및 데이터베이스의 기본적인 쿼리를 적용하는 좋은 기회였습니다.
페이징 및 파일첨부 엑셀 다운기능을 공통모듈을 만들어서 적용을  해봤던 점과 스웨거를 사용해서 api의 자동 문서화
를 해봤던 점에서 의의를 두고 있습니다.  추가적으로 jpa공부를 하고 있는 중인데 진행이 어느정도 끝이나면 해당 프로젝트에도
mybatis가 아닌 jpa로 전환을 해볼 예정입니다.

### 1-1.개선점

1.  스웨거를 적용을 했지만 특정 부분에서 응답을 달지 못한점(LocalDateTime부분)에서 찾아봐야겠다. (완료)

2.  유효성 검사를 aop를 적용해서 유효성 처리를 해보기. (완료)

3.  로그인을 폼로그인에서 jwt로그인으로 변경

4.  mybatis가 아닌 jpa로 변경하기.(완료)

5.  배포를 젠킨슨(githubaciton)과 쿠버네티스를 사용해서 배포를 하기.

### 1-2. 느낀점

- 1.개발 능력이 향상 되었다는 점.

  어찌보면 주니어 개발자입장에서는 당연한 입장이지 않을까 싶다. 계획 및 설계 부분에서도 많이 역량이 부족한 상태였지만

  프로젝트를 진행을 하면서 생기는 에러들을 해결해가면서 공부를 해가는 것도 많았었고,  각 기능을 구현하는데 필요한 로직을

  작성하는 방법과 데이터 베이스 쿼리작성 그리고 외부 api를 사용하면서 개발에 대한 숙련도를 익힐 수 있는 좋은 기회였다.

- 2.외부 api를 써봤다는 점

  프로젝트를 진행을 하면서 프로젝트의 주제가 카페의 내용을 보여주기 위해서 kakao map을 활용을 하면서 가게조회에서 가게

  위,경도 값을 넣어서 지도에 보여준점 그리고 회원가입 및 회원수정부분에 daum post api를 사용해서 주소를 입력을 할 수 있었고,

  막상 외부 api를 사용하는데 있어서 불안감이 있었는데 외부 api를 사용하면서 한층 더 자신감을 가질 수 있는 기회였다.

- 3.프로젝트 기간이 오래 걸린 점이다.

  물론 여러명이서 프로젝트를 하면 협업능력도 기를 수 있고 다른 사람과의 커뮤니케이션 능력도 기를 수 있는 좋은  기회일수 있겠지만

  여견상 개인 프로젝트로 진행을 했다는 점과 프로젝트를 하면서 특정 부분을 구현하는데 있어서 장기간의 시간이 걸렸다는 점과 개인적이

  사정으로 인해서 프로젝트가 길어졌다고 생각을 한다. 하지만 막상 프로젝트를 끝내고 난뒤 돌아보니 조금만 더 집중을 했다면 1달 이상은

  줄 일 수 있지 않았을까라는 생각이 들었다. 이런 점은 앞으로 개발자로서 안좋은 습관인것 같아 반성을 하는 계기로 삼아야겠다.


### 2. vol.01에서 개선했던점

- 스웨거에서 일부 변수 어노테이션인식 해결

- 해결: springfox-swagger2 라이브러리 안에 있는 swagger-annotations과 swagger-models의 버전충돌 문제로

           어노테이션과 모델의 라이브러리 버전을 1.5.21로 버전을 낮추는 방법과 스웨거 3.0으로 올리는 방법이 있다.

           에러문구 중에 java.lang.NumberFormatException: For input string: ""이 있는데 이 에러의 경우에는 
     
           스웨거 변수 설정에서 example로 정수를 지정하니깐 해결이 되었다.

           예) @ApiModelProperty(value="게시글번호",example="1")
  	private Integer boardId;

- aop를 활용을 해서 게시판 글작성,회원가입,가게등록 화면에 유효성검사를 하게끔했다.

- 해결

<pre>
<code>
@Log4j2
@Aspect
@Component
public class ValidatonHandler {
	
	@Around("execution(* com.kr.coffie.**..*ApiController.*(..))")
	public Object validationCheck(ProceedingJoinPoint joinpoint)throws Throwable{
		
	    String typeName = joinpoint.getSignature().getDeclaringTypeName();
	    String name = joinpoint.getSignature().getName();
	    Object[] args = joinpoint.getArgs();
		
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult)arg;
				
				if(bindingResult.hasErrors()) {
					
					Map<String,String>errorMap = new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
						String validationkey = String.format("valid_%s", error.getField());
						
						log.info(typeName + "." + name + "() => 필드 : " + error.getField() + ", 메세지 : " + error.getDefaultMessage());
						
						errorMap.put(validationkey, error.getDefaultMessage());
					}
					
					return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(),errorMap);
				}
			}
		}
		return joinpoint.proceed();
	}
}
</code>
</pre>

@Around어노테이션을 선언하고 ApiController로 시작하는 컨트롤러에 있는 모든 메소드에 유효성 검사를 실행을 하도록했으며,
컨트롤러단에 있는 BindingResult를 선언을 해서 유효성 검사를 선언을 하게 되었다. 결과적으로 화면에서 자바스크립트로
했던 유효성 검사를 걷고 코드의 양을 전반적으로 줄일수가 있었다.

- 컨트롤러에 있는 예외처리구문 대신에 전역적으로 예외처리를 하게끔 @RestControllerAdvice를 사용해서 코드를 리팩토링했다.

- 해결

<pre>
<code>
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value= Exception.class)
	public ResponseDto<?>IllegalArgumentException(Exception e)throws Exception{
		
		return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
	
}
</code>
</pre>

<pre>
<code>
@PostMapping(value="/write")
	public Map<String,Object>articleinsert(
			@ApiParam(value="게시글 객체",required = true)
			@RequestBody
			@ModelAttribute BoardDto.BoardRequestDto dto,
			@ApiIgnore
			@ModelAttribute FileDto.FileRequestDto fvo)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		int insertresult = 0;

		try {

			fvo.setImgGroup("freeBoard");
			fvo.setFileType("Board");

			insertresult = service.boardwrite(dto, fvo);
			
			if(insertresult >0) {
				result.put("Common o.k", 200);
			
			}else if(insertresult < 0) {
			
				result.put("bad request", 400);
			
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			result.put(e.getMessage(), 500);
		
		}
		
		return result;
	}
</code>
</pre>

변경후
<pre>
<code>
@PostMapping(value="/write")
	public ResponseDto<?>articleinsert(@Valid @ApiParam(value="게시글 객체",required = true) @RequestBody @ModelAttribute BoardDto.BoardRequestDto dto, 
			BindingResult bindingresult,
			@ApiIgnore @RequestBody @ModelAttribute FileDto.FileRequestDto fvo)throws Exception{
				
		int insertresult = 0;
		
		fvo.setImgGroup("freeBoard");
		fvo.setFileType("Board");
		
		insertresult = service.boardwrite(dto, fvo);
				
		return new ResponseDto<>(HttpStatus.OK.value(),200);
	}
</code>
</pre>

