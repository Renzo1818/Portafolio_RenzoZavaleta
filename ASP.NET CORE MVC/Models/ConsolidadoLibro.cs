using System;
using System.Collections.Generic;

namespace BIBLIOTECA_VIRTUAL.Models;

public partial class ConsolidadoLibro
{
    public int IdLibro { get; set; }

    public string Titulo { get; set; } = null!;

    public string Categoria { get; set; } = null!;

    public string Autor { get; set; } = null!;

    public string Editorial { get; set; } = null!;

    public int Stock { get; set; }

    public int Cantidad { get; set; }

    public DateTime FechaPublicacion { get; set; }

    public DateTime FechaRegistro { get; set; }

    public string RutaImagen { get; set; } = null!;

    public string Sinopsis { get; set; } = null!;

    public bool Estado { get; set; }
}
