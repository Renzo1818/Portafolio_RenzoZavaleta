using System;
using System.Collections.Generic;

namespace BIBLIOTECA_VIRTUAL.Models;

public partial class Categorium
{
    public int IdCategoria { get; set; }

    public string Descripcion { get; set; } = null!;

    public virtual ICollection<Libro> Libros { get; set; } = new List<Libro>();
}
