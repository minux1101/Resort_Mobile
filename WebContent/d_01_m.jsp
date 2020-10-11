<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> <!-- html에서 한글처리 -->
<%@page contentType="text/html; charset=utf-8" %>  <!-- jsp에서 한글처리 -->
<%@page import="domain.*, service.*, java.util.*, java.text.*, java.time.*, java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@fullcalendar/core@4.4.0"></script>
<link href='packages/core/main.css' rel='stylesheet' />
<link href='packages/daygrid/main.css' rel='stylesheet' />
<script src='packages/core/main.js'></script>
<script src='packages/interaction/main.js'></script>
<script src='packages/daygrid/main.js'></script>
<%
   ReserveService rs = new ReserveService();
   List<Reserve> reserveList = new ArrayList<>();
   List<Reserve> sweets = new ArrayList<>();
   List<Reserve> families = new ArrayList<>();
   List<Reserve> deluxes = new ArrayList<>();
   
   reserveList = rs.selectAll();
   LocalDate currentDate = LocalDate.now();
   int day = currentDate.getDayOfYear();
   int LengthYear = currentDate.lengthOfYear();
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   
%>
<script>
    document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
    //   eventClick: function(info) {
            // alert('Event: ' + info.event.title);
           //  alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
            // alert('View: ' + info.view.type);
     //    var title = info.event.title;
     //    var date = info.event.start;
     //    date = moment(date).format("YYYY-MM-DD");
     //    if (!title.includes('예약')){
     //       window.location.href="./d_02_insert.jsp?title=" + title + "&date=" + date;
     //    }      
         // change the border color just for fun
     //    info.el.style.borderColor = 'red';
     // },
      plugins: [ 'interaction', 'dayGrid' ],
      defaultDate: '<%=currentDate%>',
      eventColor : '#4682b4',
      editable: false,
      eventLimit: true, // allow "more" link when too many events
      events: [
         <%  
            int[] countSweet =  new int[31]; 
         int[] countFamily =  new int[31]; 
         int[] countDelux = new int[31];         
            for(Reserve r : reserveList) {
               LocalDate date = LocalDate.parse(r.getResv_date(), DateTimeFormatter.ISO_DATE);
               
               sweets = rs.selectReserveRoomList(1,r.getResv_date());
             families = rs.selectReserveRoomList(2, r.getResv_date());
             deluxes = rs.selectReserveRoomList(3, r.getResv_date());
             
             if (sweets.size() != 0 && countSweet[date.getDayOfMonth()-1] == 0) { 
                countSweet[date.getDayOfMonth()-1] = 1;
             %>
            {
              title: '스위트  <%=sweets.size()%> 개',
              start: '<%=sweets.get(0).getResv_date()%>',
             end: '<%=sweets.get(0).getResv_date()%>'
          },
        <% }%>
        <% if (families.size() != 0 && countFamily[date.getDayOfMonth()-1] == 0) { 
              countFamily[date.getDayOfMonth()-1] = 1;
        %>
           {
              title: '패밀리  <%=families.size()%> 개',
              start: '<%=families.get(0).getResv_date()%>',
             end: '<%=families.get(0).getResv_date()%>'
          },
       <% }%>
         <% if (deluxes.size() != 0 && countDelux[date.getDayOfMonth()-1] == 0) { 
               countDelux[date.getDayOfMonth()-1] = 1;
         %>
           {
              title: '디럭스 <%=deluxes.size()%> 개',
              start: '<%=deluxes.get(0).getResv_date()%>',
             end: '<%=deluxes.get(0).getResv_date()%>'
          },
        <% }
        }%>
          {
              title: '',
              start: '',
            end: ''
          }
         ]
    });
    calendar.render();
  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 20px auto;
  }

</style>
</head>
<body>

  <div id='calendar'></div>

</body>
</html>