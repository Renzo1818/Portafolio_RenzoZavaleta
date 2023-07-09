using System;
using System.Collections.Generic;

namespace BIBLIOTECA_VIRTUAL.Models;

public partial class RegistroLibrosEditorial
{
    public int IdPersona { get; set; }

    public int IdLibro { get; set; }

    public int CantidadLibros { get; set; }

    public DateTime FechaRegistro { get; set; }

    public virtual Libro IdLibroNavigation { get; set; } = null!;

    public virtual Persona IdPersonaNavigation { get; set; } = null!;
}
