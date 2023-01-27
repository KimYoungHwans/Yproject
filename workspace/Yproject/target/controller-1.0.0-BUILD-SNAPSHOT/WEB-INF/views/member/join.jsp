<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>회원가입</title>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
  <!-- Bootstrap core CSS -->
  <link href="../resources/css/bootstrap.min.css" rel="stylesheet">
  <!-- Material Design Bootstrap -->
  <link href="../resources/css/mdb.min.css" rel="stylesheet">
  <!-- Your custom styles (optional) -->
  <link href="../resources/css/style.min.css" rel="stylesheet">
  <link href="../resources/css/member/join.css" rel="stylesheet">
</head>

<style>
/* 중복아이디 존재하지 않는경우 */
	.id_input_re_1{
		color : green;
		display : none;
	}
	/* 중복아이디 존재하는 경우 */
	.id_input_re_2{
		color : red;
		display : none;
	}
	/* 유효성 검사 문구 */
 
.final_id_ck{
    display: none;
}
.final_pw_ck{
    display: none;
}
.final_pwck_ck{
    display: none;
}
.final_name_ck{
    display: none;
}
.final_mail_ck{
    display: none;
}
.final_addr_ck{
    display: none;
}
 
/* 비밀번호 확인 일치 유효성검사 */
.pwck_input_re_1{
        color : green;
        display : none;    
}
.pwck_input_re_2{
        color : red;
        display : none;    
}    
 
</style>

<body class="grey lighten-3">

	<div class="wrapper">

  <!-- Navbar -->
   <!-- Navbar -->
  <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
    <div class="container">

      <!-- Brand -->
      <a class="navbar-brand waves-effect" href="/main" >
        <strong class="green-text">StarCoffee</strong>
      </a>

      <!-- Collapse -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Links -->
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
		
		<!-- Left -->
        <ul class="navbar-nav mr-auto">
         
        </ul>
       
        <!-- Right -->
        <ul class="navbar-nav nav-flex-icons">
          <li class="nav-item">
            <a href="/cart/${member.memberId }" class="nav-link waves-effect">
              
              <i class="fas fa-shopping-cart"></i>
              <span class="clearfix d-none d-sm-inline-block"> Cart </span>
            </a>
          </li>
         
           <c:if test = "${member == null}">
          <li class="nav-item">
          
            <a href="/member/login" class="nav-link border border-light rounded waves-effect">
             LogIn
            </a>
          </li>
          <li class="nav-item">
            <a href="/member/join" class="nav-link border border-light rounded waves-effect"
              >
             Join us
            </a>
          </li>
           </c:if>
           	<c:if test="${member != null }"> 
           	
           	
           		<div class="nav-link border border-light rounded waves-effect">
           			<span>충전 금액 : <fmt:formatNumber value="${member.money}" pattern="#,###"/> </span>
           			<span>포인트 : <fmt:formatNumber value="${member.point }" pattern="#,###"/></span>
           		</div>
            <c:if test="${member.adminCk == 1 }">
            <a href="/admin/main" class="nav-link border border-light rounded waves-effect">AdminPage</a>
            </c:if>
             <a href="/member/logout.do" class="nav-link border border-light rounded waves-effect">Logout</a>
            
           </c:if> 
           
        </ul>

      </div>

    </div>
  </nav>
  <!-- Navbar -->

  <!--Main layout-->
  <main class="mt-5 pt-4">
    <div class="container wow fadeIn">

      <!-- Heading -->
      <h2 class="my-5 h2 text-center">회원가입</h2>


		<form id="join_form" method="post">

          <!--Card-->
          <div class="card" >

            

              <!--Grid row-->
              <div class="row">
				<div class="id_input_box md-form ">
					<input class="id_input form-control" name="memberId" placeholder="ID">
						<span class="id_input_re_1">사용 가능한 아이디입니다.</span>
						<span class="id_input_re_2">아이디가 이미 존재합니다.</span>
						<span class="final_id_ck">아이디를 입력해주세요.</span>
                 
				</div>
              </div>
              <!--Grid row-->

              <!--password-->
              <div class="md-form  pw_wrap">
               
                <input type="password" class="form-control  pw_input" name="memberPw" placeholder="Password" >
             	<span class="final_pw_ck">비밀번호를 입력해주세요.</span>
              </div>
              
              <!-- password check -->
              <div class="md-form input-group pwck_wrap ">
                
                <input type="password" class=" pwck_input"  placeholder="비밀번호 확인" >
              </div>
             	<span class="final_pwck_ck">비밀번호 확인을 입력해주세요.</span>
             	<span class="pwck_input_re_1">비밀번호가 일치합니다.</span>
                <span class="pwck_input_re_2">비밀번호가 일치하지 않습니다.</span>
				
				<!--address-->
              <div class="md-form user_wrap">
                <input type="text" name="memberName" class="form-control user_input" placeholder="Name">
                <span class="final_name_ck">이름을 입력해주세요.</span>
              </div>
              
              
              <!--email-->
              <div class="md-form mail_wrap">
             
                <input type="text" id="email" name="memberMail" class="form-control mail_input" placeholder="youremail@example.com" style="">
             
              </div>
                <div class="mail_check_wrap">
					
						<input class="mail_check_input " placeholder="인증번호">
				 <button class="btn-secondary  mail_check_button" type="button">인증번호 전송</button>
					
					
						
					
					  
					<div class="clearfix"></div>
					
				</div>
					<span id="mail_check_input_box_warn"></span>
				<span class="final_mail_ck">이메일을 입력해주세요.</span>
				<span class="mail_input_box_warn"></span>

              
             
          		<div class="address_button" onclick="execution_daum_address()">
						<button class="btn btn-secondary btn-md  " type="button">주소 찾기</button>
				</div>
				
				<div class="address_wrap">
					<div class="  address_input_box">
                		<input type="text" class="address_input_1" name="memberAddr1" readonly="readonly">
							<div class="clearfix"></div>
					</div>
              
              <!-- address-2 -->
             
               <div class="  address_input_box">
            	    <input type="text" class="address_input_2" name="memberAddr2" readonly="readonly">						
			   </div>
             
					
               <div class="  address_input_box">
            	    <input type="text" class=" address_input_3" name="memberAddr3" readonly="readonly">						
					<span class="final_addr_ck">상세 주소를 입력해주세요.</span>	
			   </div>
			   

          </div>    
          <!--/.Card-->
			
			
			<button class="btn btn-secondary  join_button" type="button">Join</button>
        </div>
        </form>
        </div>
        </main>
        
        
      
    
  
			<%@include file="../includes/admin/footer.jsp" %>
        
</div>
  <!--/.Footer-->

  <!-- SCRIPTS -->
  <!-- JQuery -->
  <script type="text/javascript" src="/resources/js/jquery-3.4.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="/resources/js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="/resources/js/mdb.min.js"></script>
  <!-- Initializations -->
  <script src= "https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
  <script type="text/javascript">
    // Animations initialization
    new WOW().init();
    

    var code ="";

    /* 유효성 검사 통과유무 변수 */
    var idCheck = false;            // 아이디
    var idckCheck = false;            // 아이디 중복 검사
    var pwCheck = false;            // 비번
    var pwckCheck = false;            // 비번 확인
    var pwckcorCheck = false;        // 비번 확인 일치 확인
    var nameCheck = false;            // 이름
    var mailCheck = false;            // 이메일
    var mailnumCheck = false;        // 이메일 인증번호 확인
    var addressCheck = false         // 주소



    $(document).ready(function(){
    	//회원가입 버튼(회원가입 기능 작동)
    	$(".join_button").click(function(){
    		
    		/*입력값 변수*/
    	var id = $('.id_input').val();
    	var pw = $('.pw_input').val();
    	var pwck = $('.pwck_input').val();
    	var name = $('.user_input').val();
    	var mail = $('.mail_input').val();
    	var addr = $('.address_input_3').val();
    	
    	   /* 아이디 유효성검사 */
        if(id == ""){
            $('.final_id_ck').css('display','block');
            idCheck = false;
        }else{
            $('.final_id_ck').css('display', 'none');
            idCheck = true;
        }
    	   
        /* 비밀번호 유효성 검사 */
        if(pw == ""){
            $('.final_pw_ck').css('display','block');
            pwCheck = false;
        }else{
            $('.final_pw_ck').css('display', 'none');
            pwCheck = true;
        }
        
        /* 비밀번호 확인 유효성 검사 */
        if(pwck == ""){
            $('.final_pwck_ck').css('display','block');
            pwckCheck = false;
        }else{
            $('.final_pwck_ck').css('display', 'none');
            pwckCheck = true;
        }

        /* 이름 유효성 검사 */
        if(name == ""){
            $('.final_name_ck').css('display','block');
            nameCheck = false;
        }else{
            $('.final_name_ck').css('display', 'none');
            nameCheck = true;
        }

        /* 이메일 유효성 검사 */
        if(mail == ""){
            $('.final_mail_ck').css('display','block');
            mailCheck = false;
        }else{
            $('.final_mail_ck').css('display', 'none');
            mailCheck = true;
        }
        
        /* 주소 유효성 검사 */
        if(addr == ""){
            $('.final_addr_ck').css('display','block');
            addressCheck = false;
        }else{
            $('.final_addr_ck').css('display', 'none');
            addressCheck = true;
        }
        /* 최종 유효성 검사 */
        if(idCheck&&idckCheck&&pwCheck&&pwckCheck&&pwckcorCheck&&nameCheck&&mailCheck&&mailnumCheck&&addressCheck ){

    		$("#join_form").attr("action", "/member/join");
    		$("#join_form").submit();
        }   	
        
        return false;
    	});
    });

    //아이디 중복검사
    $('.id_input').on("propertychange change keyup paste input", function(){

    	//console.log("keyup 테스트");	
    	
    	var memberId = $('.id_input').val();			// .id_input에 입력되는 값
    	var data = {memberId : memberId}				// '컨트롤에 넘길 데이터 이름' : '데이터(.id_input에 입력되는 값)'
    	
    	$.ajax({
    		type : "post",
    		url : "/member/memberIdChk",
    		data : data,
    		success : function(result){
    			if(result != 'fail'){
    				$('.id_input_re_1').css("display","inline-block");
    				$('.id_input_re_2').css("display", "none");	
    				idckCheck = true;
    			} else {
    				$('.id_input_re_2').css("display","inline-block");
    				$('.id_input_re_1').css("display", "none");	
    				idckCheck = false;
    			}
    		} // success 종료
    	}); // ajax 종료	

    });// function

    /* 인증번호 이메일 전송 */
    $(".mail_check_button").click(function(){
    	 var email = $(".mail_input").val();  
    	 var checkBox = $(".mail_check_input");
    	 var boxWrap = $(".mail_check_input_box");
    	var warnMsg = $(".mail_input_box_warn");    // 이메일 입력 경고글
    	 

        /* 이메일 형식 유효성 검사 */
        if(mailFormCheck(email)){
            warnMsg.html("이메일이 전송 되었습니다. 이메일을 확인해주세요.");
            warnMsg.css("display", "inline-block");
        } else {
            warnMsg.html("올바르지 못한 이메일 형식입니다.");
            warnMsg.css("display", "inline-block");
            return false;
        }   
    	
    	 $.ajax({
    	        
    	        type:"GET",
    	        url:"mailCheck?email=" + email,
    	       success:function(data){
    	    	 checkBox.attr("disabled",false);
    	    	 boxWrap.attr("id","mail_check_input_box_true");
    	    	 code = data;
    	       }
    	        		
    	                
    	    });
    /* 인증번호 비교 */
    $(".mail_check_input").blur(function(){
    	var inputCode = $(".mail_check_input").val();  // 입력코드  
    	var checkResult = $("#mail_check_input_box_warn");    // 비교 결과
    	
    	 if(inputCode == code && inputCode != null ){                            // 일치할 경우
    	        checkResult.html("인증번호가 일치합니다.");
    	        checkResult.attr("class", "correct");
    	        mailnumCheck = true;
    	    } else {                                            // 일치하지 않을 경우
    	        checkResult.html("인증번호를 다시 확인해주세요.");
    	        checkResult.attr("class", "incorrect");
    	        mailnumCheck = false;
    	    }    
    });
    });
     

    /* 다음 주소 연동 */
    function execution_daum_address(){
        
    	new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                
            	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
    	                // 주소변수 문자열과 참고항목 문자열 합치기
    	                addr += extraAddr;
    	            
                } else {
                   addr += ' ';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $(".address_input_1").val(data.zonecode);
                $(".address_input_2").val(addr);
           
             // 상세주소 입력란 disabled 속성 변경 및 커서를 상세주소 필드로 이동한다.
                $(".address_input_3").attr("readonly",false);
                 $(".address_input_3").focus();
     
            }
        }).open();    
    }

    /* 비밀번호 확인 일치 유효성 검사 */

    $('.pwck_input').on("propertychange change keyup paste input", function(){
    	 var pw = $('.pw_input').val();
    	    var pwck = $('.pwck_input').val();
    	    $('.final_pwck_ck').css('display', 'none');
    	    
    	    if(pw == pwck){
    	        $('.pwck_input_re_1').css('display','block');
    	        $('.pwck_input_re_2').css('display','none');
    	        pwckcorCheck = true;
    	    }else{
    	        $('.pwck_input_re_1').css('display','none');
    	        $('.pwck_input_re_2').css('display','block');
    	        pwckcorCheck = false;
    	    }        
        
    });    

    /* 입력 이메일 형식 유효성 검사 */
    function mailFormCheck(email){
    		 var form = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    		 return form.test(email);
    }
     

    </script>
</body>

</html>
