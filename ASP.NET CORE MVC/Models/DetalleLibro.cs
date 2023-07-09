using System;
using System.Collections.Generic;

namespace BIBLIOTECA_VIRTUAL.Models;

public partial class DetalleLibro
{
    public int IdLibro { get; set; }

    public string Sinopsis { get; set; } = null!;

    public virtual Libro IdLibroNavigation { get; set; } = null!;
}
