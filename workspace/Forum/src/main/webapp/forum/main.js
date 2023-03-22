$(document).ready(function () {
	
    
	//所有文章
    $('#all-article').click(function () {    
        $.ajax({
            url: '../GetAllArts',
            type: 'POST',
            success: function (data) {              
                $('#article-list').html(data);
            },
            error: function (xhr, status, error) {
                console.log('AJAX 錯誤：' + status + ' ' + error);
            }
        });
    });



//類別搜尋
$('#findCat').submit(function (event) {
        // 用ajax請求從JSP頁面獲取文章列表數據
         event.preventDefault();         
        $.ajax({
            url: '../GetByCat',
            type: 'POST',
            data: $(this).serialize(), // 提交表單數據
            success: function (data) {
                // 在主頁面中的表格中添加獲取的數據
                $('#article-list').html(data);
            },
            error: function (xhr, status, error) {
                console.log('AJAX 錯誤：' + status + ' ' + error);
            }
        });
    });

//帳號搜尋
$('#findMem').submit(function (event) {
        // 用ajax請求從JSP頁面獲取文章列表數據
         event.preventDefault();          
        $.ajax({
            url: '../GetByMem',
            type: 'POST',
            data: $(this).serialize(), // 提交表單數據
            success: function (data) {
                // 在主頁面中的表格中添加獲取的數據
                $('#article-list').html(data);
            },
            error: function (xhr, status, error) {
                console.log('AJAX 錯誤：' + status + ' ' + error);
            }
        });
    });
    
    //標題關鍵字搜尋
 $('#search-form').submit(function (e) {
    e.preventDefault(); // 防止表單默認送出
    let search = $('#search').val(); // 取得關鍵字
    $.ajax({
        url: '../SearchArt',
        type: 'POST',
        data: {search:search},
        success: function (data) {              
            $('#article-list').html(data);
        },
        error: function (xhr, status, error) {
            console.log('AJAX 錯誤：' + status + ' ' + error);
        }
    });
});
 
 
    
//新建文章
$('#new-article').click(function() {
    $('#popup-container').fadeIn();
    $.ajax({
      url: 'CreateArt.html',
      success: function(data) {
        $('#popup-content').html(data);
        $('#popup-content').fadeIn();
      },
      error: function() {
        alert('載入HTML檔案失敗');
        $('#popup-container').fadeOut();
      }
    });
  });
//彈出視窗關閉
  $('#close-popup').click(function() {
    $('#popup-container').fadeOut();
    location.reload();
  });

});

//更新文章
$('#update').click(function () {    
    $.ajax({
        url: '../GetArtForU',
        type: 'POST',
        success: function (data) {              
            $('#article-list').html(data);
        },
        error: function (xhr, status, error) {
            console.log('AJAX 錯誤：' + status + ' ' + error);
        }
    });
});