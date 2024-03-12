<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    
    
    <style>

        @font-face {
        font-family: 'NanumBarunGothic';
        font-style: normal;
        font-weight: 900;
        src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot');
        src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot?#iefix') format('embedded-opentype'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.woff') format('woff'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.ttf') format('truetype');
        }
        
        div.editable {
            height: 300px;
            overflow-y: auto;
            width : 70%;
            border : 1px solid lightgray;
            border-radius: 5px;
            background-color: white;
            color: rgb(90, 90, 90);
            margin : auto;
            font-size: 12px;
        }

        .wrap {
            background-color: white;
            width: 80%;
            margin: auto;
            font-family: 'NanumBarunGothic';
        }

        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #e0e0e0;
        }

        .content {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }


/*회원가입폼 */

.outer {
		width : 1000px;
		margin : auto;
		margin-top : 50px;
		border : 1px dotted lightgray;
	}

	#enroll-form table { margin : auto; }
	#enroll-form input { margin : 5px; 
        border : 1px solid lightgray;
                         border-radius: 5px;
                   color: #585858;
                       
    }

    #enroll-form #checked1, #enroll-form #checked2{

        margin-left: 200px;
    }

    #user b{
        color : rgb(255, 123, 0)
    }
    #user button{
        width: 100px;
        height : 45px;
        border-radius: 5px;
        border : 1px solid lightgray;
    }
    

    #user hr{
        margin-top: 20px;
    }

    #user hr{
        margin-top: 20px;
    }

  
   
    </style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

    <div class="wrap">
   

        <div class="content">

        
         <div id="user">

                <h2 align="center" style="padding-top: 50px; margin-bottom: 30px;">회원가입</h2> <br>
            
        
                <!-- 
                    회원가입 요청 시 url
                    http://localhost:8888/jsp/insert.me
                -->
                <form id="enroll-form" action="<%= contextPath %>/insert.me" method="post">
        
                    <!--
                        회원가입 시 입력받아야하는 것들
                        아이디, 비밀번호, 이름, 전화번호, 이메일, 주소, 취미
                    -->
        
                    <table>
                        <tr>
                            <td></td>
                            <td style="font-size: 17px;"> &nbsp;&nbsp;&nbsp;&nbsp;<b>*</b> 이용약관 및 개인정보 수집 이용에 대한 안내 동의</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <div class="editable" style="padding: 30px">
                                    <h4>제 1장 총칙</h4>

                                    <h5>제 1 조(목적)</h5>
                                    본 약관은 LMC 웹사이트(이하 "LMC")가 제공하는 모든 서비스(이하 "서비스")의 이용조건 및 절차, 회원과 LMC의 권리, 의무, 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다.<br>
                                    
                                    <br><br>

                                    <h5>제 2 조(약관의 효력과 변경)</h5>
                                    1. LMC는 이용자가 본 약관 내용에 동의하는 경우, LMC의 서비스 제공 행위 및 회원의 서비스 사용 행위에 본 약관이 우선적으로 적용됩니다.<br>
                                    2. LMC는 약관을 개정할 경우, 적용일자 및 개정사유를 명시하여 현행약관과 함께 LMC의 초기화면에 그 적용일 7일 이전부터 적용 전일까지 공지합니다. 단, 회원에 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 LMC는 개정 전 내용과 개정 후 내용을 명확하게 비교하여 회원이 알기 쉽도록 표시합니다.<br>
                                    3. 변경된 약관은 LMC 홈페이지에 공지하거나 e-mail을 통해 회원에게 공지하며, 약관의 부칙에 명시된 날부터 그 효력이 발생됩니다. 회원이 변경된 약관에 동의하지 않는 경우, 회원은 본인의 회원등록을 취소(회원탈퇴)할 수 있으며, 변경된 약관의 효력 발생일로부터 7일 이내에 거부의사를 표시하지 아니하고 서비스를 계속 사용할 경우는 약관 변경에 대한 동의로 간주됩니다.<br>

                                    <br><br>
                                    
                                    <h5>제 3 조(약관 외 준칙)</h5>
                                    본 약관에 명시되지 않은 사항은 전기통신기본법, 전기통신사업법, 정보통신윤리위원회심의규정, 정보통신 윤리강령, 프로그램보호법 및 기타 관련 법령의 규정에 의합니다.<br>

                                    <br><br>
                                    
                                    <h5>제 4 조(용어의 정의)</h5>
                                    본 약관에서 사용하는 용어의 정의는 다음과 같습니다.<br>
                                    1. 이용자 : 본 약관에 따라 LMC이 제공하는 서비스를 받는 자<br>
                                    2. 가입 : LMC이 제공하는 신청서 양식에 해당 정보를 기입하고, 본 약관에 동의하여 서비스 이용계약을 완료시키는 행위<br>
                                    3. 회원 : LMC에 개인 정보를 제공하여 회원 등록을 한 자로서 LMC이 제공하는 서비스를 이용할 수 있는 자.<br>
                                    4. 계정(ID) : 회원의 식별과 회원의 서비스 이용을 위하여 회원이 선정하고 LMC에서 부여하는 문자와 숫자의 조합<br>
                                    5. 비밀번호 : 회원과 계정이 일치하는지를 확인하고 통신상의 자신의 비밀보호를 위하여 회원 자신이 선정한 문자와 숫자의 조합<br>
                                    6. 탈퇴 : 회원이 이용계약을 종료시키는 행위<br>
                                    7. 본 약관에서 정의하지 않은 용어는 개별서비스에 대한 별도 약관 및 이용규정에서 정의합니다.<br>


                                    <br><br>
                                    
                                    <h4>제 2장 서비스 제공 및 이용</h4>
                                    
                                    <h5>제 5 조 (이용계약의 성립)</h5>
                                    1. 이용계약은 이용자가 온라인으로 LMC에서 제공하는 소정의 가입신청 양식에서 요구하는 사항을 기록하여 가입을 완료하는 것으로 성립됩니다.<br>
                                    2. LMC는 다음 각 호에 해당하는 이용계약에 대하여는 가입을 취소할 수 있습니다.<br>
                                    1) 다른 사람의 명의를 사용하여 신청하였을 때<br>
                                    2) 이용계약 신청서의 내용을 허위로 기재하였거나 신청하였을 때<br>
                                    3) 다른 사람의 LMC 서비스 이용을 방해하거나 그 정보를 도용하는 등의 행위를 하였을 때<br>
                                    4) LMC을 이용하여 법령과 본 약관이 금지하는 행위를 하는 경우<br>
                                    5) 기타 LMC이 정한 이용신청요건이 미비 되었을 때<br>

                                    <br><br>
                                    
                                    <h5>제 6 조 (회원정보 사용에 대한 동의)</h5>
                                    1. 회원의 개인정보는 「공공기관의개인정보보호에관한법률」에 의해 보호됩니다.<br>
                                    2. LMC의 회원 정보는 다음과 같이 사용, 관리, 보호됩니다.<br>
                                    1) 개인정보의 사용 : LMC는 서비스 제공과 관련해서 수집된 회원의 신상정보를 본인의 승낙 없이 제3자에게 누설, 배포하지 않습 니다. 단, 전기통신기본법 등 법률의 규정에 의해 국가기관의 요구가 있는 경우, 범죄에 대한 수사상의 목적이 있거나 정보통신윤리위원회의 요청이 있는 경우 또는 기타 관계법령에서 정한 절차에 따른 요청이 있는 경우, 이용자 스스로 개인정보를 공개한 경우에는 그러 하지 않습니다.<br>
                                    2) 개인정보의 관리 : 회원은 개인정보의 보호 및 관리를 위하여 서비스의 개인정보관리에서 수시로 회원의 개인정보를 수정/삭제할 수 있습니다.<br>
                                    3) 개인정보의 보호 : 회원의 개인정보는 오직 본인만이 열람/수정/삭제 할 수 있으며, 이는 전적으로 회원의 계정과 비밀번호에 의해 관리되고 있습니다. 따라서 타인에게 본인의 계정과 비밀번호를 알려주어서는 안되며, 작업 종료시에는 반드시 로그아웃해 주시기 바랍니다.<br>
                                    3. 회원이 본 약관에 따라 이용신청을 하는 것은, LMC의 신청서에 기재된 회원정보를 수집, 이용하는 것에 동의하는 것으로 간주 됩니다.<br>

                                    <br><br>
                                    
                                    <h5>제 7 조 (사용자의 정보 보안)</h5>
                                    1. 이용자는 LMC 서비스 가입 절차를 완료하는 순간부터 본인이 입력한 정보의 비밀을 유지할 책임이 있으며, 회원이 고의나 중대한 실수로 회원의 계정과 비밀번호를 사용하여 발생한 피해에 대한 책임은 회원 본인에게 있습니다.<br>
                                    2. 계정과 비밀번호에 관한 모든 관리의 책임은 회원에게 있으며, 회원의 계정이나 비밀번호가 부정하게 사용되었다는 사실을 발견한 경우에는 즉시 LMC에 신고하여야 합니다. 신고를 하지 않음으로 인한 모든 책임은 회원 본인에게 있습니다.<br>
                                    3. 이용자는 LMC 서비스의 사용 종료시마다 정확히 접속을 종료해야 하며, 정확히 종료하지 아니함으로써 제3자가 이용자에 관한 정보를 이용하게 되는 등의 결과로 인해 발생하는 손해 및 손실에 대하여 LMC는 책임을 부담하지 아니합니다.<br>

                                    <br><br>
                                    
                                    <h5>제 8 조 (서비스의 변경)</h5>
                                    1. 당 사이트는 귀하가 서비스를 이용하여 기대하는 손익이나 서비스를 통하여 얻은 자료로 인한 손해에 관하여 책임을 지지 않으며, 회원이 본 서비스에 게재한 정보, 자료, 사실의 신뢰도, 정확성 등 내용에 관하여는 책임을 지지 않습니다.<br>
                                    2. 당 사이트는 서비스 이용과 관련하여 가입자에게 발생한 손해 중 가입자의 고의,과실에 의한 손해에 대하여 책임을 부담하지 아니합니다.<br>

                                    <br><br>
                                    
                                    <h5>제 9 조 (이용기간 및 자격의 정지 및 상실)</h5>
                                    1. LMC 회원이용기간은 조직통폐합에 따른 불가항력을 제외하고 회원신청에서 탈퇴까지로 합니다.<br>
                                    2. LMC는 이용자가 본 약관에 명시된 내용을 위배하는 행동을 한 경우, 이용자격을 일시적으로 정지하고 30일 이내에 시정하도록 이용자에게 요구할 수 있으며, 이후 동일한 행위를 2회 이상 반복할 경우에 30일간의 소명기회를 부여한 후 이용자격을 상실시킬 수 있습니다.<br>
                                    3. LMC 회원이 신청 후 12개월이상 장시간 이용하지 않은 회원은 휴면아이디로 분류하여, 자격 정지 및 상실이 가능합니다.<br>

                                    <br><br>
                                    
                                    <h4>제 10 조 (계약해제, 해지 등)</h4>
                                    1. 회원은 언제든지 서비스 초기화면의 마이페이지 또는 정보수정 메뉴 등을 통하여 이용계약 해지 신청을 할 수 있으며, LMC는 관련법 등이 정하는 바에 따라 이를 즉시 처리하여야 합니다.<br>
                                    2. 회원이 계약을 해지할 경우, 관련법 및 개인정보취급방침에 따라 LMC이 회원정보를 보유하는 경우를 제외하고는 해지 즉시 회원의 모든 데이터는 소멸됩니다.<br>
                                    3. 회원이 계약을 해지하는 경우, 회원이 작성한 게시물 중 블로그 등과 같이 본인 계정에 등록된 게시물 일체는 삭제됩니다. 단, 타인에 의해 스크랩 되어 재게시되거나, 공용게시판에 등록된 게시물 등은 삭제되지 않으니 사전에 삭제 후 탈퇴하시기 바랍니다.<br>

                                    <br><br>
                                    
                                    <h5>제 11 조 (게시물의 저작권)</h5>
                                    1. 이용자가 게시한 게시물의 저작권은 이용자가 소유하며, LMC는 서비스 내에 이를 게시할 수 있는 권리를 갖습니다.<br>
                                    2. LMC는 다음 각 호에 해당하는 게시물이나 자료를 사전통지 없이 삭제하거나 이동 또는 등록 거부를 할 수 있습니다.<br>
                                    1) 본서비스 약관에 위배되거나 상용 또는 불법, 음란, 저속하다고 판단되는 게시물을 게시한 경우<br>
                                    2) 다른 회원 또는 제 3자에게 심한 모욕을 주거나 명예를 손상시키는 내용인 경우<br>
                                    3) 공공질서 및 미풍양속에 위반되는 내용을 유포하거나 링크시키는 경우<br>
                                    4) 불법복제 또는 해킹을 조장하는 내용인 경우<br>
                                    5) 영리를 목적으로 하는 광고일 경우<br>
                                    6) 범죄와 결부된다고 객관적으로 인정되는 내용일 경우<br>
                                    7) 다른 이용자 또는 제 3자의 저작권 등 기타 권리를 침해하는 내용인 경우<br>
                                    8) LMC에서 규정한 게시물 원칙에 어긋나거나, 게시판 성격에 부합하지 않는 경우<br>
                                    9) 기타 관계법령에 위배된다고 판단되는 경우<br>
                                    3. 이용자의 게시물이 타인의 저작권을 침해함으로써 발생하는 민, 형사상의 책임은 전적으로 이용자가 부담하여야 합니다.<br>

                                    <br><br>
                                    

                                    <h4>제 3장 의무 및 책임</h4>
                                    
                                    <h5>제 12 조 (LMC의 의무)</h5>
                                    1. LMC는 회원의 개인 신상 정보를 본인의 승낙없이 타인에게 누설, 배포하지 않습니다. 단, 전기통신관련법령 등 관계법령에 의하여 관계 국가기관 등의 요구가 있는 경우에는 그러하지 아니합니다.<br>
                                    2. LMC는 법령과 본 약관이 금지하거나 미풍양속에 반하는 행위를 하지 않으며, 지속적·안정적으로 서비스를 제공하기 위해 노력 할 의무가 있습니다.<br>
                                    3. LMC는 이용자의 귀책사유로 인한 서비스 이용 장애에 대하여 책임을 지지 않습니다.<br>

                                    <br><br>
                                    
                                    <h5>제 13 조 (회원의 의무)</h5>
                                    1. 회원 가입시에 요구되는 정보는 정확하게 기입하여야 합니다. 또한 이미 제공된 회원에 대한 정보가 정확한 정보가 되도록 유지, 갱신하여야 하며, 회원은 자신의 계정 및 비밀번호를 제3자에게 이용하게 해서는 안됩니다.<br>
                                    2. 회원은 LMC의 사전 승낙없이 서비스를 이용하여 어떠한 영리행위도 할 수 없으며, 그 영업활동의 결과에 대해 LMC는 일절 책임을 지지 않습니다. 또한 회원은 이와 같은 영업활동으로 LMC이 손해를 입은 경우 손해배상의무를 지며, LMC는 해당 회원에 대해 서비스 이용제한 및 적법한 절차를 거쳐 손해배상 등을 청구할 수 있습니다.<br>
                                    3. 회원은 LMC 서비스 이용과 관련하여 다음 각 호의 행위를 하여서는 안됩니다.<br>
                                    1) 회원가입 신청 또는 회원정보 변경 시 허위내용을 기재하거나 다른 회원의 비밀번호와 ID를 도용하여 부정 사용하는 행위<br>
                                    2) 저속, 음란, 모욕적, 위협적이거나 타인의 Privacy를 침해할 수 있는 내용을 전송, 게시, 게재, 전자우편 또는 기타의 방법으로 전송하는 행위<br>
                                    3) LMC 운영진, 직원 또는 관계자를 사칭하는 행위<br>
                                    4) 서비스를 통하여 전송된 내용의 출처를 위장하는 행위<br>
                                    5) 법률, 계약에 의해 이용할 수 없는 내용을 게시, 게재, 전자우편 또는 기타의 방법으로 전송하는 행위<br>
                                    6) 서버 해킹 및 컴퓨터바이러스 유포, 웹사이트 또는 게시된 정보의 일부분 또는 전체를 임의로 변경하는 행위<br>
                                    7) 타인의 특허, 상표, 영업비밀, 저작권, 기타 지적재산권을 침해하는 내용을 게시, 게재, 전자우편 또는 기타의 방법으로 전송하는 행위<br>
                                    8) LMC의 승인을 받지 아니한 광고, 판촉물, 스팸메일, 행운의 편지, 피라미드 조직 기타 다른 형태의 권유를 게시, 게재, 전자우편 또는 기타의 방법으로 전송하는 행위<br>
                                    9) 다른 사용자의 개인정보를 수집, 저장, 공개하는 행위<br>
                                    10) 범죄행위를 목적으로 하거나 기타 범죄행위와 관련된 행위<br>
                                    11) 선량한 풍속, 기타 사회질서를 해하는 행위<br>
                                    12) 타인의 명예를 훼손하거나 모욕하는 행위<br>
                                    13) 타인의 지적재산권 등의 권리를 침해하는 행위<br>
                                    14) 타인의 의사에 반하여 광고성 정보 등 일정한 내용을 지속적으로 전송하는 행위<br>
                                    15) 서비스의 안정적인 운영에 지장을 주거나 줄 우려가 있는 일체의 행위<br>
                                    17) 본 약관을 포함하여 기타 LMC이 정한 제반 규정 또는 이용 조건을 위반하는 행위<br>
                                    18) 기타 관계법령에 위배되는 행위<br>


                                    <br><br>
                                    
                                    <h4>제 4장 기타</h4>
                                    
                                    <h5>제 14 조 (양도금지)</h5>
                                    회원이 서비스의 이용권한, 기타 이용계약 상 지위를 타인에게 양도, 증여할 수 없습니다.<br>

                                    <br><br>
                                    
                                    <h5>제 15조 (면책조항)</h5>
                                    1. LMC는 서비스 이용과 관련하여 이용자에게 발생한 손해에 대하여 LMC의 중대한 과실, 고의 또는 범죄행위로 인해 발생한 손해를 제외하고 이에 대하여 책임을 부담하지 않으며, 이용자가 본 서비스에 게재한 정보, 자료, 사실의 신뢰도, 정확성 등 내용에 관하여는 책임을 지지 않습니다.<br>
                                    2. LMC는 서비스 이용과 관련하여 이용자에게 발생한 손해 중 이용자의 고의, 실수에 의한 손해에 대하여 책임을 부담하지 아니합니다.<br>
                                    3. LMC는 이용자간 또는 이용자와 제3자간에 서비스를 매개로 하여 물품거래 혹은 금전적 거래 등과 관련하여 어떠한 책임도 부담하지 아니하고, 이용자가 서비스의 이용과 관련하여 기대하는 이익에 관하여 책임을 부담하지 않습니다.<br>

                                    <br><br>
                                    
                                    <h5>제 16 조 (재판관할)</h5>
                                    LMC과 이용자간에 발생한 서비스 이용에 관한 분쟁에 대하여는 대한민국 법을 적용하며, 본 분쟁으로 인한 소는 민사소송법상의 관할법원에 제기합니다.<br>
                                    부 칙 1. (시행일) 본 약관은 2023년 04월 03일부터 시행됩니다.<br>
                                </div></td>
                                
                        </tr>
                        
                        <tr>
                            <td colspan="2" style="font-size: 17px;"> <input class="terms" id="checked1" type="checkbox" name="check1"><b>위의 내용을 모두 읽었으며 동의합니다.</b><br><br></td>
                            <td></td>
                        </tr>
                
                        <tr>
                            <td colspan="3"> <div class="editable" style="padding: 30px">
                                <h5>가. 개인정보의 수집 및 이용 목적</h4>
                                ① LMC는 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 개인정보 보호법 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.<br>
                                1. LMC 서비스 제공을 위한 회원관리<br>
                                1) 공간정보 다운로드, 오픈API 신청 및 활용 등 포털 서비스 제공과 서비스 부정이용 방지를 목적으로 개인정보를 처리합니다.<br>
                                
                                <br><br>
                                    
                                <h5>나. 정보주체와 법정대리인의 권리ㆍ의무 및 행사방법</h5>
                                ① 정보주체(만 14세 미만인 경우에는 법정대리인을 말함)는 언제든지 개인정보 열람·정정·삭제·처리정지 요구 등의 권리를 행사할 수 있습니다.<br>
                                ② 제1항에 따른 권리 행사는 개인정보보호법 시행규칙 별지 제8호 서식에 따라 작성 후 서면, 전자우편 등을 통하여 하실 수 있으며, 기관은 이에 대해 지체 없이 조치하겠습니다.<br>
                                ③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 개인정보 보호법 시행규칙 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.<br>
                                ④ 개인정보 열람 및 처리정지 요구는 개인정보 보호법 제35조 제5항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.<br>
                                ⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.<br>
                                ⑥ 정보주체 권리에 따른 열람의 요구, 정정ㆍ삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.<br>
                                
                                <br><br>
                                    
                                <h5>다. 수집하는 개인정보의 항목</h5>
                                ① LMC 회원정보(필수): 이름, 이메일(아이디), 비밀번호<br>
                                
                                <br><br>
                                    
                                <h5>라. 개인정보의 보유 및 이용기간</h5>
                                ① LMC는 법령에 따른 개인정보 보유ㆍ이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의 받은 개인정보 보유ㆍ이용기간 내에서 개인정보를 처리ㆍ보유합니다.<br>
                                1. LMC 회원정보<br>
                                - 수집근거: 정보주체의 동의<br>
                                - 보존기간: 회원 탈퇴 요청 전까지(1년 경과 시 재동의)<br>
                                - 보존근거: 정보주체의 동의<br>
                                
                                <br><br>

                                <h5>마. 동의 거부 권리 및 동의 거부에 따른 불이익</h5>
                                위 개인정보의 수집 및 이용에 대한 동의를 거부할 수 있으나, 동의를 거부할 경우 회원 가입이 제한됩니다.<br> </div></td>
                        </tr>
                        <tr>
                        
                            <td colspan="2" style="font-size: 17px;"> <input class="terms" id="checked2" type="checkbox"  name="check2"><b>위의 내용을 모두 읽었으며 동의합니다.</b></td>
                            <td></td>
                        </tr>

                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>


                        
                        <tr>
                           <td style="font-size: 17px;">&nbsp;<b>*</b> 아이디</td>
                           <td>
	                           <input type="text" name="userId"  minlength="3" maxlength="20" required
	                           style="height : 35px; font-size: 15px; padding: 10px;" id="userId">   
	                           
	                           <button type="button" class="btn btn-primary"  id="duplicationCheck" disabled="true"
                               style="font-size: 13px; height: 35px;"
	                           >중복확인</button>
                           </td>
                           <td>
                                
                            

                            </td>
                        </tr>
                        
              

                        <tr>
                            <td></td>
                            <td id="result1" style="color : gray; font-size: 13px;">
                                <hr>
                                &nbsp;&nbsp;&nbsp;&nbsp;  회원 ID는 3~20자 사이의 영문+숫자로 이루어져야 하며 영문(소문자)로 시작해야 합니다.
                            </td>
                            <td>
                            
                            </td>
                        </tr>
                        
                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>
                        
                        <tr>
                            <td style="font-size: 17px;">&nbsp;<b>*</b> 비밀번호</td>
                            <td>
                                <input type="password" name="userPwd" minlength="6" maxlength="20" required
                                style="height : 35px; font-size: 15px; padding: 10px;" id="userPwd1">
                            </td>
                            
                            <td>
                                

                            </td>
                        </tr>

                        <tr>
                            <td></td>
                            <td id="result2" style="color : gray; font-size: 13px;">
                                <hr>
                                &nbsp;&nbsp;&nbsp;&nbsp;   비밀번호는 6자리 이상이어야 하며 영문과 숫자를 반드시 포함해야 합니다.
                            </td>
                            <td>
                            
                            </td>
                        </tr>

                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>

                        <tr>
                            <td style="font-size: 17px;">&nbsp;<b>*</b> 비밀번호확인</td>
                            <td>
                                <input type="password" maxlength="20" required
                                style="height : 35px; font-size: 15px; padding: 10px;" id="userPwd2" name="checkPwd"
                                disabled="true">
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td style="font-size: 13px;" id="result3">
                       
                            </td>
                            <td>
                            
                            </td>
                        </tr>


                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>

                        <tr>
                            <td style="font-size: 17px;"><b>*</b> 이름</td>
                            <td>
                                <input type="text" name="userName" maxlength="6" required
                                style="height : 35px; font-size: 15px; padding: 10px;" id="userName">
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td style="font-size: 13px;" id="result4">
                       
                            </td>
                            <td>
                            
                            </td>
                        </tr>

                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>

                        <tr>
                            <td style="font-size: 17px;"><b>*</b> 닉네임</td>
                            <td>
                                <input type="text" name="Nname" maxlength="6" required
                                style="height : 35px; font-size: 15px; padding: 10px;" id="Nname">

                                <button type="button" style="font-size: 13px; height: 35px;" 
		                           class="btn btn-primary"  id="duplicationNickCheck"
		                           disabled="true"
		                           >중복확인</button>
                            </td>
                            <td style="font-size: 13px;" id="result5"></td>
                        </tr>

                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>

                        <tr>
                            <td style="font-size: 17px;">&nbsp;<b>*</b> 이메일</td>
                               <td>
		                           <input type="text" name="email1"  minlength="3" maxlength="20" required
		                           style="height : 35px; font-size: 15px; padding: 10px;" id="email1">   
		                           
		                           <button type="button" style="font-size: 13px; height: 35px;"
		                           class="btn btn-primary"  id="duplicationEmailCheck"
		                           disabled="true"
		                           >중복확인</button>
	                           </td>
                            <td style="font-size: 13px;" id="result6"></td>
                        </tr>

                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>

                        
                        <tr>
                            <td style="font-size: 17px;">&nbsp;&nbsp;&nbsp;&nbsp;전화번호</td>
                            <td>
                                <input type="text" name="phone" placeholder="- 포함해서 입력"
                                style="height : 35px; font-size: 15px; padding: 10px;">
                            </td>
                            <td>
                                
                            

                            </td>
                        </tr>

                        <tr>
                            <td></td>
                            <td style="font-size: 13px;">
                                
                                &nbsp;&nbsp;&nbsp;&nbsp;    전화번호 없이 가입할 수 있으나, 글이나 댓글을 쓰려면 번호 인증을 거쳐야 합니다.
                            </td>
                            <td>
                            
                            </td>
                        </tr>

                        <tr>
                            <td colspan="3"><hr></td>
                        </tr>

                        
                        <tr>
                        
                        </tr>
                    </table>


                    <br>
	                    <div align="center">
	                        <button type="submit" id="finalSubmit" style="width: 200px; height: 50px;" 
	                        class="btn btn-primary"  onclick="return validate();" disabled="true">회원가입</button>
	                    </div>
 
					
					
                    <script>

                        
                            ///////////////////////////////////////
                         //   회원 가입 요구 조건 확인 기능 부   /////
                        //////////////////////////////////////
                        
                        $(function(){
                        		// green 1~n 각 조건을 모두 만족 했을 때 가입 버튼의 disabled 속성 풀어주기
                        		let greenAgree = 0;  // 회원가입 약관 동의
                        		let greenId = 0;	 // 아이디 중복 체크
                        		let greenPass = 0;	 // 비밀번호 조건 체크
                        		let greenName = 0;  // 이름 입력 여부
                        		let greenNick = 0;  // 닉네임 입력 여부
                        		let greenEmail = 0; // 이메일 입력 여부
                        		
                        		// 회원 가입 가능 여부를 최종 결정하는 객체(greenLight) 생성 양수면 회원 가입 그린 라이트!!
                        		let greenLight = 0;
                        		
                           		// 그린라이트가 양수 일 때만 회원가입 버튼이 활성화 되도록 설정
                        		// 각 조건을 입력했을 떄 마다 작동하도록 함수화
                        		function finalCheck(){
                           			
                        			greenLight = greenAgree*greenId*greenPass*greenName*greenNick*greenEmail;
                           			
									if(greenLight>0){
	                        			$("#finalSubmit").attr("disabled", false);
	                        		} else {
	                        			$("#finalSubmit").attr("disabled", true);
	                        		}
                        		}
                        		
                        		                       		
                        		
                        		// 조건 1
                        		// 회원 동의 약관 체크 여부에 따른 회원 가입 버튼 잠금 처리
                        		$(".terms").click(function(){
		                       		if($('#checked1').is(':checked') && $('#checked2').is(':checked')){
		                       			
		                       			greenAgree = 1;
		                       			finalCheck();
		                       		} else {
		                       			greenAgree = 0;
		                       		}
		                       		console.log("greenAgree 값 정상:" + greenAgree);
                        		});
                        		
                        		// 조건 2
                        		// 아이디 요구 조건 일치 여부 (일치 했을 때만 중복 체크를 가능하도록)
                        		$("#userId").keyup(function() {
                        		    let userId = $(this).val();
                                    // 아이디 조건 한글 영문 숫자 같이 사용가능 6 ~20 
                                    let checkId = RegExp(/^[a-z0-9]{3,20}$/);
                                    
                                    if(checkId.test(userId)) {
                                        $("#result1").text("유효한 형식의 아이디입니다.").css("color", "green");
                                      	$("#duplicationCheck").attr("disabled", false);
                                    } else {
                                        $("#result1").text("회원 ID는 3~20자 사이의 영문+숫자로 이루어져야 하며 영문(소문자)로 시작해야 합니다.").css("color", "red");
                                        $("#duplicationCheck").attr("disabled", true);
                                    }
                        		});
                        		
                        		// 조건 3
                        		// 아이디 중복 체크 확인 ( 중복 체크를 해야지만 회원 가입 버튼 열림)
                        		$("#duplicationCheck").click(function(){
                        			
	                        		$.ajax({
										url : "idCheck.do",
										type : "get",
										data : {
											checkId : $("#userId").val()
											},
										success : function(result){
											
											// console.log(result);
											// result 값에 따라 성공 여부 결정 하기
											if(result>0){
											$("#result1").text("중복된 아이디 입니당! ㅇㅅ<").css("color", "red");
											greenId = 0;
											} else {
											$("#result1").text("사용가능한 아이디 입니당! ㅇㅅ<").css("color", "green");
											greenId = 1;
											finalCheck();
											console.log("greenId 값 정상:" + greenId);
											}
										}
	                        		}); //ajax 끝
	                        	
                        		}); // duplicationCheck 클릭 이벤트 끝
                        		
                        		
                        		// 조건 4
                        		// 비밀번호 조건 추가 (영문+숫자 가 6-20) > 올바른 형식일 때만 비밀번호 확인 창 열림
                        		 $("#userPwd1").keyup(function() {
                                    let userPwd1 = $(this).val();
                                    let check_pw = RegExp(/^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/);
                                    
                                    if(!check_pw.test(userPwd1)) {
                                    	$("#result2").text("비밀번호는 6~20자리여야 하며 영문과 숫자를 반드시 포함해야 합니다.").css("color", "red");
                                    	$("#userPwd2").attr("disabled", true);
                                    } else {
                                    	$("#result2").text("유효한 형식의 비밀번호입니다.").css("color", "green");
                                    	$("#userPwd2").attr("disabled", false);
                                    }
                                });
                        		
                        		
                        		// 조건 5 
                        		// 비밀번호 확인 일치 여부 확인
                        		$("#userPwd2").keyup(function() {
                        			let userPwd1 = $("#userPwd1").val();
                                    let userPwd2 = $(this).val();
                                    if(userPwd2 == userPwd1) {
                                    	
                                    	$("#result3").text("비밀번호 확인 완료 ㅇㅅ<")
                                         			.css("color", "green");
                                    	$("#userPwd2").attr("disabled", true);
                                    	greenPass=1;
                                    	finalCheck();
                                    	
                                    } else {
                                        $("#result3").text("동일한 비밀번호를 입력해주세요")
                                                    .css("color", "red");
                                        
                                        $("#userPwd2").attr("disabled", false);
                                    }
                                });
                        		
                        		// 조건 6
                        		// 이름 조건 (한글) 로만 2글자 이상
                        		$("#userName").keyup(function() {
                                    let userName = $(this).val();
                                    let checkName = RegExp(/^[가-힣]{2,5}$/);
                                    				
                                    
                                	if(!checkName.test(userName)) { 
                                		
                                        $("#result4").text("한글 (결합) 로만 이루어져야 하며 2글자 이상으로 입력하세요.").css("color", "red");
                                	
                                    } else {
                                    	$("#result4").text("이거슨 훌륭한 이름 ㅇㅅ<").css("color", "green");
                                		greenName=1;
                                		finalCheck();
                                    }
                                
                                });
                        		
                        		// 조건7 
                        		// 닉네임(중복x)
                        		$("#Nname").keyup(function() {
                                    let userNick = $(this).val();
                                    // 닉네임 조건 한글 영문 숫자 같이 사용가능 2~10 
                                    let checkNick = RegExp(/^[가-힣a-zA-Z0-9]{2,10}$/);
                                    
                                	if(!checkNick.test(userNick)) { 
                                		
                                        $("#result5").text("닉네임 조건은 한글,영문,숫자만 사용 가능하고 2~10글자로 입력하세요.").css("color", "red");
                                    	$("#duplicationNickCheck").attr("disabled", true);
                                        
                                    } else {
                                    	$("#result5").text("닉 훌륭 :D").css("color", "green");
                                		$("#duplicationNickCheck").attr("disabled", false);
                                	}
                                });
                        		
                        		// 닉네임 중복 확인용 ajax
								$("#duplicationNickCheck").click(function(){
                        			
	                        		$.ajax({
										url : "nickCheck.do",
										type : "get",
										data : {
											checkNick : $("#Nname").val()
											},
										success : function(result){
											
											// console.log(result);
											// result 값에 따라 성공 여부 결정 하기
											if(result > 0){
											$("#result5").text("중복된 닉 입니당! ㅇㅅ<").css("color", "red");
											greenNick = 0;
											} else {
											$("#result5").text("사용가능한 닉 입니당! ㅇㅅ<").css("color", "green");
											greenNick = 1;
											finalCheck();
											
											console.log("greenId 값 정상:" + greenId);
											}
										}
	                        		}); //ajax 끝
	                        	
                        		}); // duplicationNickCheck 클릭 이벤트 끝
                        		
                        		// 조건8
                        		// 이메일(중복x)
                        		$("#email1").keyup(function() {
                                    let userEmail = $(this).val();
                                    // 닉네임 조건 한글 영문 숫자 같이 사용가능 2~10 
                                    let checkEmail = RegExp(/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/);
                                    
                                	if(!checkEmail.test(userEmail)) { 
                                		
                                        $("#result6").text("메?일").css("color", "red");
                                    	$("#duplicationEmailCheck").attr("disabled", true);
                                        
                                    } else {
                                    	$("#result6").text("성공 ㅇㅅ<").css("color", "green");
                                		$("#duplicationEmailCheck").attr("disabled", false);
                                	}
                                });
                        		
                        		
								$("#duplicationEmailCheck").click(function(){
                        			
	                        		$.ajax({
										url : "emailCheck.do",
										type : "get",
										data : {
											checkEmail : $("#email1").val()
											},
										success : function(result){
											
											// console.log(result);
											// result 값에 따라 성공 여부 결정 하기
											if(result > 0){
											$("#result6").text("중복된 메일 입니당! ㅇㅅ<").css("color", "red");
											greenEmail = 0;
											} else {
											$("#result6").text("사용가능한 메일 입니당! ㅇㅅ<").css("color", "green");
											greenEmail = 1;
											finalCheck();
											}
										}
	                        		}); //ajax 끝
								}); // 중복체크 메소드 끝
                        		
                 
                        	}); // 제이쿼리끝

                            $(function() {
                    
                                // 1. select 요소 선택
                                let select = $("#email-select");
                    
                                select.change(function() {
                    
                                    // console.log("잘되나?");
                                    let email = $("input[name=email2]");
                    
                                    // 현재 클릭을 당한 select 의 내용물을 추출
                                    // => email 변수의 value 속성으로 넣기
                    
                                    let content = $(this).val();
                                    // console.log(content);
                    
                                    email.val(content);
                    
                                    if(content == "직접입력") {
                    
                                        email.val("");
                                        email.attr("readonly", false);
                                    } else {
                                        email.attr("readonly", true);
                                    }
                                });
                            });

                        // 유효성 검사 : 사용자가 입력한 값들이 유효한 값의 형태인지
                        function validate() {
                
                            // 아이디, 비밀번호, 비밀번호 일치, 이름
                
                            // input 요소 객체들을 담기 (작성된 값 아님!!)
                            const userId = document.getElementById("userId"); // <input>
                            const userPwd1 = document.getElementById("userPwd1"); // <input>
                            const userPwd2 = document.getElementById("userPwd2"); // <input>
                            const userName = document.getElementById("userName"); // <input>
                
                            // 1) 아이디 검사
                            // 첫글자는 반드시 영문자(소문자) 로,
                            // 그리고 영문자, 숫자를 포함하여 총 3~20자로 입력하시오.
                            let regExp = /^[a-z][a-z\d]{2,19}$/;
                
                            if(!regExp.test(userId.value)) {
                
                                alert("유효한 아이디를 입력해주세요.");
                                userId.select();
                                return false;
                            }
                
                            // 2) 비밀번호 검사
                            //비밀번호는 6~15이어야 하며 영문과 숫자를 반드시
                            //포함해야 합니다.
                            regExp = /^[a-z\d!@#$%^&*]{6,20}$/i;
                
                            if(!regExp.test(userPwd1.value)) {
                
                                alert("유효한 비밀번호를 입력하세요");
                                userPwd1.value = ""; // 기존에 입력받은 값 날리기
                                userPwd1.focus(); // 입력창에 커서를 넣어줌
                                return false;
                            }
                
                            // 3) 비밀번호 일치
                            // 위의 비밀번호와 일치하게 입력하시오.
                            if(userPwd1.value != userPwd2.value) {
                
                                alert("동일한 비밀번호를 입력해주세요");
                                userPwd2.value = "";
                                userPwd2.focus();
                                return false;
                            }
                
                            // 4) 이름 검사
                            // 한글 (결합) 로만 이루어져야 하며 2글자 이상으로 입력하시오.
                            regExp = /^[가-힣]{2,}$/;
                
                            if(!regExp.test(userName.value)) {
                
                                alert("유효한 이름을 입력해 주세요");
                                userName.select(); // 내용물을 드래그해주는 효과
                                return false;
                            }
                            
                      
                
                            return true; // 생략 가능
                
                        }
                    
                    $(function() {
                    	$(".editable").prop("readonly", "true");
                    })
					
                  
				        $('.editable').each(function(){
				  
				    });
                        
                    </script>
        
                    <br><br>
        
                </form>
         </div>

        </div>

    </div>

    <%@ include file = "../common/footer.jsp" %>

</body>
</html>