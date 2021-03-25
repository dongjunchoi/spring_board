<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="UTF-8">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Tables - SB Admin</title>
        <link href="${cp}/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
          <link rel="stylesheet" href="${cp}/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
		<!-- Theme style -->
		<link rel="stylesheet" href="${cp}/resources/bootstrap/dist/css/adminlte.min.css">
        <style type="text/css">
        	.pagination{
        		margin: auto;
        		width: 300px;
        	}
        </style>
        
        <script>
			$(function(){
				$(".boardview").on("click",function(){
					//this : 클릭 이벤트가 발생한 element
					// data-속성명 data-userid, 속성명은 대소문자 무시하고 소문자로 인식
					// data-userId ==> data-userid
					var userid = $(this).data("userid");
					var boardno = $(this).data("boardno");
					var postno = $(this).data("postno");
					var delcheck = $(this).data("delcheck");
					if(delcheck == 0){
						alert("삭제된 게시글 입니다.");
						return false;
					}else{
						$("#userid").val(userid);
						$("#boardno").val(boardno);
						$("#postno").val(postno);
					}
					$("#frm").submit();
				})
			})
		</script>
    </head>
    <body class="sb-nav-fixed">
    	<form id="frm" action="${cp}/board/boardPostView">
    		<input type="hidden" id="userid" name="userId" >
    		<input type="hidden" id="boardno" name="boardNo" >
    		<input type="hidden" id="postno" name="postNo" >
    	</form>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand" href="${cp}/board/mainController">게시판 사이트</a>
            <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" />
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="button"><i class="fas fa-search"></i></button>
                    </div>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="#">Settings</a>
                        <a class="dropdown-item" href="#">Activity Log</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${cp}/user/logout">Logout</a>
                    </div>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                        	<div class="sb-sidenav-menu-heading">내정보</div>
                            <a class="nav-link" href="${cp}/board/registBoardInfo">
                                <div class="sb-nav-link-icon">
                                	<img src="${cp}/user/profile?userid=${S_USER.userid}" style="width: 40px; height: 40px;" class="img-circle elevation-2" alt="User Image">
                                </div> 
                                ${S_USER.usernm}
                            </a>
                        
                            <div class="sb-sidenav-menu-heading">게시판관리</div>
                            <a class="nav-link" href="${cp}/board/registBoardInfo">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                게시판 생성
                            </a>
                            <div class="sb-sidenav-menu-heading">게시판 목록</div>
                            <c:forEach items="${boardInfoList}" var="boardinfo">
                            	<c:if test="${boardinfo.bor_act==1}">
                            		<a class="nav-link collapsed" href="${cp}/board/boardView?boardNo=${boardinfo.bor_no}" >
		                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
		                                ${boardinfo.bor_name}
		                            </a>
                            	</c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        Start Bootstrap
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
                        <h1 class="mt-4">${boardInfo.bor_name}</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">${boardInfo.bor_name} 페이지 입니다.</li>
                        </ol>
                        
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table mr-1"></i>
                                게시글 목록
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered"  width="100%" cellspacing="0">
                                        
                                            <tr>
                                                <th>게시글 번호</th>
                                                <th>제목</th>
                                                <th>작성자 아이디</th>
                                                <th>작성일시</th>
                                            </tr>
                                      
                                        <c:forEach items="${boardList}" var="board" varStatus="status">
                                            <tr class = "boardview" data-boardno="${board.bor_no}" data-userid="${board.user_id}" data-postno="${board.post_no}" data-delcheck="${board.post_del}">
                                                <td>${status.count+(pageVo.page-1)*10}</td>
                                                <td>
	                                                <c:choose>
	                                                	<c:when test="${board.post_del==0}">
	                                                		삭제된 게시글입니다.
	                                                	</c:when>
	                                                	<c:otherwise>
		                                                <c:forEach begin="1" end="${board.boardLevel}" var="i">
		                                                	<c:if test="${i > 1}">
		                                                		&ensp; &ensp;
		                                                	</c:if>
		                                                </c:forEach>
		                                                <c:if test="${board.boardLevel > 1}">
		                                                		↳
		                                                	</c:if>
			                                            ${board.title}
			                                            </c:otherwise>
			                                        </c:choose>
		                                        </td> 
		                                                                                       
                                                
                                                <td>${board.user_id}</td>
                                             	<td><fmt:formatDate value="${board.reg_dt}" pattern="yyyy-MM-dd"/></td>
                                            </tr>
                                       	</c:forEach>
                                    </table>
                                
                            </div>
                            <a class="page-link" href="${cp}/board/registBoard?boardNo=${boardNo}" style="float: right;" >게시글 등록</a>
                            
			                        		
			                        	
                        </div>
                       
                        <div class="card-body" >
                        	<c:if test="${not empty boardList}">
	                        <ul class="pagination">
	                        	
	                        	<c:choose>
									<c:when test="${pageVo.page-1<1}">
										<li class="paginate_button page-item next" id="dataTable_next">
											<span class="page-link"><<</span>
			                        	</li>
										<li class="paginate_button page-item next" id="dataTable_next">
											<span class="page-link"><</span>
			                        	</li>
									</c:when>
									<c:otherwise>			
										<li class="paginate_button page-item next" id="dataTable_next">
			                        		<a href="${cp}/board/boardView?page=${1}&pageSize=${pageVo.pageSize}&boardNo=${boardNo}" class="page-link"><<</a>
			                        	</li>							
										<li class="paginate_button page-item next" id="dataTable_next">
			                        		<a href="${cp}/board/boardView?page=${pageVo.page-1}&pageSize=${pageVo.pageSize}&boardNo=${boardNo}" class="page-link"><</a>
			                        	</li>
									</c:otherwise>
								</c:choose>
                        	
	                        	
	                        	
	                        	
	                        	
	                        	<c:forEach begin="1" end="${pagination}" var="i">
	                        		<c:choose>
	                        			<c:when test="${i == pageVo.page}">
	                        				<li class="paginate_button page-item active">
		                        				<span class="page-link">${i}</span>
		                        			</li>
	                        			</c:when>
	                        			<c:otherwise>
			                        		<li class="paginate_button page-item">
			                        			<a href="${cp}/board/boardView?page=${i}&pageSize=${pageVo.pageSize}&boardNo=${boardNo}" class="page-link">${i}</a>
			                        		</li>
	                        			</c:otherwise>
	                        		</c:choose>
	                        	</c:forEach>
	                        	
	                        	<c:choose>
									<c:when test="${pageVo.page+1>pagination}">
										<li class="paginate_button page-item next" id="dataTable_next">
											<span class="page-link">></span>
			                        	</li>
			                        	<li class="paginate_button page-item next" id="dataTable_next">
											<span class="page-link">>></span>
			                        	</li>
										
									</c:when>
									<c:otherwise>										
										<li class="paginate_button page-item next" id="dataTable_next">
			                        		<a href="${cp}/board/boardView?page=${pageVo.page+1}&pageSize=${pageVo.pageSize}&boardNo=${boardNo}" class="page-link">></a>
			                        	</li>
			                        	<li class="paginate_button page-item next" id="dataTable_next">
			                        		<a href="${cp}/board/boardView?page=${pagination}&pageSize=${pageVo.pageSize}&boardNo=${boardNo}" class="page-link">>></a>
			                        	</li>
									</c:otherwise>
								</c:choose>
	                        	
	                        	
	                       
	                        	
	                        	
	                        </ul>
	                        </c:if>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2020</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/datatables-demo.js"></script>
    </body>
</html>
