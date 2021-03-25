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
        <title>Dashboard - SB Admin</title>
        <link href="${cp}/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
        
          <link rel="stylesheet" href="${cp}/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
		<!-- Theme style -->
		<link rel="stylesheet" href="${cp}/resources/bootstrap/dist/css/adminlte.min.css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        
        <script>
        	$(function() {
				$("#comentBoard").on("click",function(){
					$("#cboardno").val("${postVo.bor_no}");
					$("#cpostno").val("${postVo.post_no}");
					$("#cuserid").val("${postVo.user_id}");
					$("#frm").attr("method","GET");
					$("#frm").attr("action","${cp}/board/comentBoardPost");
					$("#frm").submit();
				});
				$("#deleteBoard").on("click",function(){
					$("#cboardno").val("${postVo.bor_no}");
					$("#cpostno").val("${postVo.post_no}");
					$("#cuserid").val("${postVo.user_id}");	
					$("#frm").attr("method","POST");
					$("#frm").attr("action","${cp}/board/deleteBoardPost");
					$("#frm").submit();
				});
				
				$("#modifyBoard").on("click",function(){
					$("#cboardno").val("${postVo.bor_no}");
					$("#cpostno").val("${postVo.post_no}");
					$("#cuserid").val("${postVo.user_id}");	
					$("#frm").attr("method","GET");
					$("#frm").attr("action","${cp}/board/modifyBoardPost");
					$("#frm").submit();
				});
				
			});
        </script>
        
        
    </head>
    <body class="sb-nav-fixed">
    	<form id="frm">
    		<input type="hidden" id="cboardno" name="cBoardNo">
    		<input type="hidden" id="cpostno" name="cPostNo">
    		<input type="hidden" id="cuserid" name="cUserId">
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
                        <h1 class="mt-4">${postVo.title}</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">작성자 : ${postVo.user_id}<br>작성일 : <fmt:formatDate value="${postVo.reg_dt}" pattern="yyyy-MM-dd"/></li>
                        </ol>
                  
                        <div class="card mb-1">
                            <div class="card-header">
                                <i class="fas fa-table mr-1"></i>
                                내용
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                	${postVo.cont}
                                	첨부파일<br>
                                	<c:forEach items="${fileList}" var="file">
                                		<a href="${cp}/board/fileDownload?attNo=${file.att_no}&boardNo=${file.bor_no}&postNo=${file.post_no}&userId=${file.user_id}">
											${file.file_nm}
										</a><br>
                                	</c:forEach>
                                	
                                	<input type="button" style="float: right;" id="comentBoard" value="답글">
                                	<c:if test="${S_USER.userid == postVo.user_id}">
                                		<input type="button" style="float: right;" id="deleteBoard" value="삭제">
                                		<input type="button" style="float: right;" id="modifyBoard" value="수정">
                                		
                                	</c:if>
                                	
                                </div>
                            </div>
                        </div>
                        
                        <div class="card mb-1">
                            <div class="card-header">
                                <i class="fas fa-table mr-1"></i>
                                댓글
                            </div>
                            <div class="card-body">
                            	<div class="table-responsive">
                            		<c:forEach items="${commentList}" var="comment">
                            			<c:choose>
                            				<c:when test="${comment.com_del==1}">
		                            			<form action="${cp}/board/deleteComment" method="post">
			                            			${comment.com_con} [${comment.com_user_id}/<fmt:formatDate value="${postVo.reg_dt}" pattern="yyyy-MM-dd"/>]
			                            			<c:if test="${S_USER.userid == comment.com_user_id}">
				                                		<input type="submit" value="삭제">
				                                		<input type="hidden" name="comNo" value="${comment.com_no}">
				                                		<input type="hidden" name="boardNo" value="${postVo.bor_no}"> 
				                                		<input type="hidden" name="postNo" value="${postVo.post_no}"> 
				                                		<input type="hidden" name="rUserId" value="${postVo.user_id}"> 
			                                		</c:if>
			                            			<br>
		                            			</form>
	                            			</c:when>
	                            			<c:otherwise>
	                            			삭제된 댓글입니다.<br>
	                            			</c:otherwise>
                            			</c:choose>
                            		</c:forEach>
                            		<br><br>
                            	</div>
                                <div class="table-responsive">
                                	<form action="${cp}/board/registComent" method="post">
                                		<input type="hidden" name="userId" value="${S_USER.userid}">
                                		<input type="hidden" name="boardNo" value="${postVo.bor_no}"> 
                                		<input type="hidden" name="postNo" value="${postVo.post_no}"> 
                                		<input type="hidden" name="rUserId" value="${postVo.user_id}"> 
	                                	<textarea name="comment" rows="5" cols="150"></textarea>
	                                	<!-- <input type="text" style="width: 70%;" name="coment"> -->
	                                	<input type="submit" value="댓글 저장">
                                	</form>
                                </div>
                            </div>
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
        <script src="${cp}/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="${cp}/assets/demo/chart-area-demo.js"></script>
        <script src="${cp}/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
        <script src="${cp}/assets/demo/datatables-demo.js"></script>
    </body>
</html>
