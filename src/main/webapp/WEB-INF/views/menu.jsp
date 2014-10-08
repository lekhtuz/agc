<!-- http://sqeets.com/Tutorial/create-drop-down-menu-in-html-using-css -->

<nav>
	<ul>
		<li>
			<a href="#">Jobs</a>
			<ul>
				<li>
					<a href="#">Log</a>
					<ul>
						<li><a href="#">New Log Entry</a></li>
						<li><a href="#">Find Job Log</a></li>
						<li><a href="#">Update Log Status By Number</a></li>
						<li><a href="#">Job Orders Entered By Date</a></li>
						<li><a href="#">Print Folder</a></li>
						<li><a href="#">Print Pending Fax</a></li>
						<li><a href="#">Job Distribution Analysis</a></li>
					</ul>
				</li>
				<li>
					<a href="#">Pictures</a>
					<ul>
						<li><a href="#">Assign Install Pictures to Job Order</a></li>
						<li><a href="#">Assign Measure Pictures to Job Log</a></li>
						<li><a href="#">Transfer Daily Measure Pictures</a></li>
					</ul>
				</li>
				<li>
					<a href="#">Production</a>
					<ul>
						<li><a href="#">Customer SignOff Document</a></li>
						<li><a href="#">Custom Glass</a></li>
						<li><a href="#">Find Job Order</a></li>
						<li><a href="#">Find Job Orders Plating Status</a></li>
						<li><a href="#">Priority</a></li>
					</ul>
				</li>
				<li>
					<a href="#">MDI Scheduling</a>
					<ul>
						<li><a href="#">Print Installer Schedule</a></li>
					</ul>
				</li>
				<li>
					<a href="#">Quality Control</a>
					<ul>
						<li><a href="#">Find Problem Log</a></li>
					</ul>
				</li>
				<li>
					<a href="#">Shipping</a>
					<ul>
						<li><a href="#">Pring Shipping Label</a></li>
						<li><a href="#">Load Truck By ShipCode</a></li>
						<li><a href="#">Reroute Jobs On TruckRun/ShipCode(s)</a></li>
						<li><a href="#">Process Manifest</a></li>
					</ul>
				</li>
			</ul>
		</li>
		<li>
			<a href="#">Tables</a>
			<ul>
				<li><a href="#">Order Entry</a></li>
				<li><a href="#">Engineering</a></li>
				<li><a href="#">Inventory</a></li>
				<li>
					<a href="#">Pricing</a>
					<ul>
						<li><a href='<%=request.getContextPath()+"/pricing"%>'>Generate Base Price Tables</a></li>
						<li><a href="#">Charge Item List</a></li>
						<li><a href="#">Finish Groups</a></li>
						<li><a href="#">Handle Definition and Upgrade Pricing</a></li>
						<li><a href="#">Handle Finish and Add On Pricing</a></li>
						<li><a href="#">Price Groups</a></li>
					</ul>
				</li>
				<li><a href="#">Vendor</a></li>
			</ul>
		</li>
		<li>
			<a href="#">Accounting</a>
			<ul>
				<li><a href="#">Accounts Payable</a></li>
				<li><a href="#">Accounts Receivable</a></li>
				<li><a href="#">Job Log Costing Report</a></li>
				<li><a href="#">Sales and Commission</a></li>
				<li><a href="#">Sales and Use Tax</a></li>
				<li><a href="#">Spiff Program</a></li>
			</ul>
		</li>
	</ul>
</nav>
