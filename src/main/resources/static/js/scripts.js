// Reload page on Reports page
function reloadPage() {
    // get year and quarter selected
    const year = document.getElementById('year').value;
    const quarter = document.getElementById('quarter').value;
    
    // redirect to the reports page with the new year and quarter in URL
    location.href = '/reports/' + year + '/' + quarter;
}