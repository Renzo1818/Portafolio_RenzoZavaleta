using System;
using System.Collections.Generic;

namespace BIBLIOTECA_VIRTUAL.Models;

public partial class Libro
{
    public int IdLibro { get; set; }

    public int IdAutor { get; set; }

    public int IdEditorial { get; set; }

    public int IdCategoria { get; set; }

    public string Titulo { get; set; } = null!;

    public int Stock { get; set; }

    public DateTime FechaPublicacion { get; set; }

    public string RutaImgLibro { get; set; } = null!;

    public bool Estado { get; set; }

    public virtual DetalleLibro? DetalleLibro { get; set; }

    public virtual ICollection<DetalleReserva> DetalleReservas { get; set; } = new List<DetalleReserva>();

    public virtual Autor IdAutorNavigation { get; set; } = null!;

    public virtual Categorium IdCategoriaNavigation { get; set; } = null!;

    public virtual Editorial IdEditorialNavigation { get; set; } = null!;

    public virtual ICollection<RegistroLibrosEditorial> RegistroLibrosEditorials { get; set; } = new List<RegistroLibrosEditorial>();
}
