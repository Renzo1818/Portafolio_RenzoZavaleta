<script>
    $(document).ready(function() {
        $('.carousel').slick({
            infinite: true,
            slidesToShow: 3, // Número de elementos visibles en el carrusel
            slidesToScroll: 1, // Número de elementos para avanzar o retroceder
            autoplay: true, // Desplazamiento automático
            autoplaySpeed: 2000, // Intervalo de tiempo entre diapositivas (en milisegundos)
            responsive: [
                {
                    breakpoint: 992, // Cambiar el número de elementos visibles en una pantalla más pequeña
                    settings: {
                        slidesToShow: 2
                    }
                },
                {
                    breakpoint: 768, // Cambiar el número de elementos visibles en una pantalla aún más pequeña
                    settings: {
                        slidesToShow: 1
                    }
                }
            ]
        });
});
</script>
