using System;
using System.Collections.Generic;

namespace BIBLIOTECA_VIRTUAL.Models;

public partial class ConsolidadoReserva
{
    public int IdReserva { get; set; }

    public int IdPersona { get; set; }

    public int IdLibro { get; set; }

    public string Titulo { get; set; } = null!;

    public string Autor { get; set; } = null!;

    public string Categoria { get; set; } = null!;

    public string Editorial { get; set; } = null!;

    public DateTime FechaInicio { get; set; }

    public DateTime FechaFinal { get; set; }

    public bool Estado { get; set; }
}
