<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="urlHome" />
<footer id="footer" class="top-space">
	<div class="footer1">
		<div class="container">
			<div class="row">

				<div class="col-md-9 widget">
					<h3 class="widget-title">Contato</h3>
					<div class="widget-body">
						<p>
							(11) 5851-9315<br>
							<!-- a href="mailto:#">some.email@somewhere.com</a -->
							Rua Frederico Grotte, 322 - Jardim São Luis, São Paulo/SP
						</p>
					</div>
				</div>

				<div class="col-md-3 widget">
					<h3 class="widget-title">Siga-me</h3>
					<div class="widget-body">
						<p class="follow-me-icons">
							<a href=""><i class="fa fa-twitter fa-2"></i></a> <a href=""><i
								class="fa fa-dribbble fa-2"></i></a> <a href=""><i
								class="fa fa-github fa-2"></i></a> <a href=""><i
								class="fa fa-facebook fa-2"></i></a>
						</p>
					</div>
				</div>

			</div>
			<!-- /row of widgets -->
		</div>
	</div>

	<div class="footer2">
		<div class="container">
			<div class="row">

				<div class="col-md-6 widget">
					<div class="widget-body">
						<p class="simplenav">
							<a href="${urlHome}">Home</a> | <a
								href="${urlHome}sistema?c=Contato">Contato</a>
						</p>
					</div>
				</div>

				<div class="col-md-6 widget">
					<div class="widget-body">
						<p class="text-right">
							Copyright &copy; 2015, André Santos. Desenvolvido por <a href="#"
								rel="designer">André Santos</a>
						</p>
					</div>
				</div>

			</div>
			<!-- /row of widgets -->
		</div>
	</div>

</footer>

<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="assets/js/headroom.min.js"></script>
<script type="text/javascript" src="assets/js/template.js"></script>
<script type="text/javascript" src="assets/js/jQuery.headroom.min.js"></script>
<script type="text/javascript" src="assets/js/lightbox-plus-jquery.js"></script>
<script type="text/javascript" src="assets/js/scripts.js"></script>