<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> <!-- html에서 한글처리 -->
<%@page contentType="text/html; charset=utf-8" %>  <!-- jsp에서 한글처리 -->
<%@page import="domain.*, service.*, java.util.*, java.text.*, java.time.*" %>
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
   RoomCountService rCountService = new RoomCountService();
   //rCountService.delete();
   rCountService.makeData();
   
   RoomCount room1 = new RoomCount();
   RoomCount room2 = new RoomCount();
   RoomCount room3 = new RoomCount();
   
   List<Reserve> reserveList = new ArrayList<>();
   LocalDate currentDate = LocalDate.now();
   int day = currentDate.getDayOfYear();
   int LengthYear = currentDate.lengthOfYear();
   List<RoomCount> sweets = new ArrayList<>();
   List<RoomCount> familes = new ArrayList<>();
   List<RoomCount> deluxes = new ArrayList<>();
   sweets = rCountService.selectAll("sweet");
   familes = rCountService.selectAll("family");
   deluxes = rCountService.selectAll("delux");
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   List<Reserve> list = rs.selectToday(currentDate);
  
%>
<script>
    document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        eventClick: function(info) {
            // alert('Event: ' + info.event.title);
           //  alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
            // alert('View: ' + info.view.type);
         var title = info.event.title;
         var date = info.event.start;
         date = moment(date).format("YYYY-MM-DD");
         if (!title.includes('예약')){
            window.location.href="./d_02_insert_m.jsp?title=" + title + "&date=" + date;
         }      
         // change the border color just for fun
         info.el.style.borderColor = 'red';
      },
      plugins: [ 'interaction', 'dayGrid' ],
      defaultDate: '<%=currentDate%>',
      editable: false,
      eventColor : '#4682b4',
      eventLimit: true, // allow "more" link when too many events
      events: [      
          <% for(int i=0; i < 30; i++) { 
                 if (sweets.get(i).getNum() != 0) { %>
              {
              title: '스위트',
              start: '<%=sweets.get(i).getDate()%>',
                end: '<%=sweets.get(i).getDate()%>'
              },
          <% } 
                if (familes.get(i).getNum() != 0) { %>
              {
              title: '패밀리',
              start: '<%=familes.get(i).getDate()%>',
                end: '<%=familes.get(i).getDate()%>'
              },
          <%    } 
                 if (deluxes.get(i).getNum() != 0) { %>
             {
               title: '디럭스',
               start: '<%=deluxes.get(i).getDate()%>',
                 end: '<%=deluxes.get(i).getDate()%>'
             },
         <%    }
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