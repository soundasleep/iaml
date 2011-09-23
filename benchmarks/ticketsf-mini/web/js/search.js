$(document).ready(function()
{
	$('#event_search_q').keyup(function(key)
	{
	  if (this.value.length >= 3 || this.value == '')
	  {
		$('#events_search_results').load(
		  $(this).parents('form').attr('action'), { 'q': this.value }
		);
	  }
	});
});
