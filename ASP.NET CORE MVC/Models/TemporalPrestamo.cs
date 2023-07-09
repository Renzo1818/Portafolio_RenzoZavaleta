namespace BIBLIOTECA_VIRTUAL.Models
{
    public class TemporalPrestamo
    {
        public int codigoLibro { get; set; }
        public int codigoPersona { get; set; }
        public string titulo { get; set; }
        public string autor { get; set; }
        public string editorial { get; set; }
        public string categoria { get; set; }
        public int cantidad { get; set; }
        public DateTime fechaInicio { get; set; }
        public DateTime fechaFin { get; set; }
        public bool estado { get; set; }

    }
}
