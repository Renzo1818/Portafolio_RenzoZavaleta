using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace BIBLIOTECA_VIRTUAL.Models;

public partial class OperaC : DbContext
{
    public OperaC()
    {
    }

    public OperaC(DbContextOptions<OperaC> options)
        : base(options)
    {
    }

    public virtual DbSet<Autor> Autors { get; set; }

    public virtual DbSet<Categorium> Categoria { get; set; }

    public virtual DbSet<ConsolidadoLibro> ConsolidadoLibros { get; set; }

    public virtual DbSet<ConsolidadoReserva> ConsolidadoReservas { get; set; }

    public virtual DbSet<DetalleLibro> DetalleLibros { get; set; }

    public virtual DbSet<DetalleReserva> DetalleReservas { get; set; }

    public virtual DbSet<Distrito> Distritos { get; set; }

    public virtual DbSet<Editorial> Editorials { get; set; }

    public virtual DbSet<Libro> Libros { get; set; }

    public virtual DbSet<Persona> Personas { get; set; }

    public virtual DbSet<RegistroLibrosEditorial> RegistroLibrosEditorials { get; set; }

    public virtual DbSet<ReservaVirtual> ReservaVirtuals { get; set; }

    public virtual DbSet<TipoPersona> TipoPersonas { get; set; }

    public virtual DbSet<Usuario> Usuarios { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see http://go.microsoft.com/fwlink/?LinkId=723263.
        => optionsBuilder.UseSqlServer("Data Source=SQL8005.site4now.net;Initial Catalog=db_a9b33f_biblioteca;User Id=db_a9b33f_biblioteca_admin;Password=dragon123;Integrated Security=False;Connect Timeout=30;Encrypt=False;Trust Server Certificate=False;Application Intent=ReadWrite;Multi Subnet Failover=False");

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.UseCollation("Modern_Spanish_CI_AS");

        modelBuilder.Entity<Autor>(entity =>
        {
            entity.HasKey(e => e.IdAutor).HasName("PK_ID_AUTOR");

            entity.ToTable("AUTOR");

            entity.HasIndex(e => e.NombreApellido, "UQ_NOMBRE_APELLIDO").IsUnique();

            entity.Property(e => e.IdAutor).HasColumnName("ID_AUTOR");
            entity.Property(e => e.Estado).HasColumnName("ESTADO");
            entity.Property(e => e.FechaNacimiento)
                .HasColumnType("date")
                .HasColumnName("FECHA_NACIMIENTO");
            entity.Property(e => e.Nacionalidad)
                .HasMaxLength(20)
                .IsUnicode(false)
                .HasColumnName("NACIONALIDAD");
            entity.Property(e => e.NombreApellido)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("NOMBRE_APELLIDO");
        });

        modelBuilder.Entity<Categorium>(entity =>
        {
            entity.HasKey(e => e.IdCategoria).HasName("PK_ID_CATEGORIA");

            entity.ToTable("CATEGORIA");

            entity.HasIndex(e => e.Descripcion, "UQ_DESCRIPCION").IsUnique();

            entity.Property(e => e.IdCategoria).HasColumnName("ID_CATEGORIA");
            entity.Property(e => e.Descripcion)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("DESCRIPCION");
        });

        modelBuilder.Entity<ConsolidadoLibro>(entity =>
        {
            entity
                .HasNoKey()
                .ToView("CONSOLIDADO_LIBRO");

            entity.Property(e => e.Autor)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("AUTOR");
            entity.Property(e => e.Cantidad).HasColumnName("CANTIDAD");
            entity.Property(e => e.Categoria)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("CATEGORIA");
            entity.Property(e => e.Editorial)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("EDITORIAL");
            entity.Property(e => e.Estado).HasColumnName("ESTADO");
            entity.Property(e => e.FechaPublicacion)
                .HasColumnType("date")
                .HasColumnName("FECHA_PUBLICACION");
            entity.Property(e => e.FechaRegistro)
                .HasColumnType("datetime")
                .HasColumnName("FECHA_REGISTRO");
            entity.Property(e => e.IdLibro).HasColumnName("ID_LIBRO");
            entity.Property(e => e.RutaImagen)
                .HasMaxLength(200)
                .IsUnicode(false)
                .HasColumnName("RUTA_IMAGEN");
            entity.Property(e => e.Sinopsis)
                .HasMaxLength(400)
                .IsUnicode(false)
                .HasColumnName("SINOPSIS");
            entity.Property(e => e.Stock).HasColumnName("STOCK");
            entity.Property(e => e.Titulo)
                .HasMaxLength(100)
                .IsUnicode(false)
                .HasColumnName("TITULO");
        });

        modelBuilder.Entity<ConsolidadoReserva>(entity =>
        {
            entity
                .HasNoKey()
                .ToView("CONSOLIDADO_RESERVA");

            entity.Property(e => e.Autor)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("AUTOR");
            entity.Property(e => e.Categoria)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("CATEGORIA");
            entity.Property(e => e.Editorial)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("EDITORIAL");
            entity.Property(e => e.Estado).HasColumnName("ESTADO");
            entity.Property(e => e.FechaFinal)
                .HasColumnType("datetime")
                .HasColumnName("FECHA_FINAL");
            entity.Property(e => e.FechaInicio)
                .HasColumnType("datetime")
                .HasColumnName("FECHA_INICIO");
            entity.Property(e => e.IdLibro).HasColumnName("ID_LIBRO");
            entity.Property(e => e.IdPersona).HasColumnName("ID_PERSONA");
            entity.Property(e => e.IdReserva).HasColumnName("ID_RESERVA");
            entity.Property(e => e.Titulo)
                .HasMaxLength(100)
                .IsUnicode(false)
                .HasColumnName("TITULO");
        });

        modelBuilder.Entity<DetalleLibro>(entity =>
        {
            entity.HasKey(e => e.IdLibro).HasName("PK_ID_LIBRO_SINOPSIS");

            entity.ToTable("DETALLE_LIBRO");

            entity.HasIndex(e => e.Sinopsis, "UQ_SINOPSIS").IsUnique();

            entity.Property(e => e.IdLibro)
                .ValueGeneratedNever()
                .HasColumnName("ID_LIBRO");
            entity.Property(e => e.Sinopsis)
                .HasMaxLength(400)
                .IsUnicode(false)
                .HasColumnName("SINOPSIS");

            entity.HasOne(d => d.IdLibroNavigation).WithOne(p => p.DetalleLibro)
                .HasForeignKey<DetalleLibro>(d => d.IdLibro)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_LIBRO_SINOPSIS");
        });

        modelBuilder.Entity<DetalleReserva>(entity =>
        {
            entity.HasKey(e => new { e.IdReserva, e.IdLibro }).HasName("PK_ID_DETALLE_RESERVA");

            entity.ToTable("DETALLE_RESERVA");

            entity.Property(e => e.IdReserva).HasColumnName("ID_RESERVA");
            entity.Property(e => e.IdLibro).HasColumnName("ID_LIBRO");
            entity.Property(e => e.Cantidad).HasColumnName("CANTIDAD");
            entity.Property(e => e.Estado).HasColumnName("ESTADO");

            entity.HasOne(d => d.IdLibroNavigation).WithMany(p => p.DetalleReservas)
                .HasForeignKey(d => d.IdLibro)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_LIBRO_DETALLE");

            entity.HasOne(d => d.IdReservaNavigation).WithMany(p => p.DetalleReservas)
                .HasForeignKey(d => d.IdReserva)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_RESERVA_DETALLE");
        });

        modelBuilder.Entity<Distrito>(entity =>
        {
            entity.HasKey(e => e.IdDistrito).HasName("PK_ID_DISTRITO");

            entity.ToTable("DISTRITOS");

            entity.Property(e => e.IdDistrito).HasColumnName("ID_DISTRITO");
            entity.Property(e => e.Descripcion)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("DESCRIPCION");
        });

        modelBuilder.Entity<Editorial>(entity =>
        {
            entity.HasKey(e => e.IdEditorial).HasName("PK_ID_EDITORIAL");

            entity.ToTable("EDITORIAL");

            entity.HasIndex(e => e.CorreoElec, "UQ_CORREO_ELEC").IsUnique();

            entity.HasIndex(e => e.Direccion, "UQ_DIRECCION").IsUnique();

            entity.HasIndex(e => e.RazonSocial, "UQ_RAZON_SOCIAL").IsUnique();

            entity.HasIndex(e => e.Ruc, "UQ_RUC").IsUnique();

            entity.Property(e => e.IdEditorial).HasColumnName("ID_EDITORIAL");
            entity.Property(e => e.CorreoElec)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("CORREO_ELEC");
            entity.Property(e => e.Direccion)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("DIRECCION");
            entity.Property(e => e.Estado).HasColumnName("ESTADO");
            entity.Property(e => e.RazonSocial)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("RAZON_SOCIAL");
            entity.Property(e => e.Ruc)
                .HasMaxLength(11)
                .IsUnicode(false)
                .IsFixedLength()
                .HasColumnName("RUC");
        });

        modelBuilder.Entity<Libro>(entity =>
        {
            entity.HasKey(e => e.IdLibro).HasName("PK_ID_LIBRO");

            entity.ToTable("LIBROS");

            entity.HasIndex(e => e.RutaImgLibro, "UQ_RUTA_IMG_LIBRO").IsUnique();

            entity.HasIndex(e => e.Titulo, "UQ_TITULO_LIBRO").IsUnique();

            entity.Property(e => e.IdLibro).HasColumnName("ID_LIBRO");
            entity.Property(e => e.Estado).HasColumnName("ESTADO");
            entity.Property(e => e.FechaPublicacion)
                .HasColumnType("date")
                .HasColumnName("FECHA_PUBLICACION");
            entity.Property(e => e.IdAutor).HasColumnName("ID_AUTOR");
            entity.Property(e => e.IdCategoria).HasColumnName("ID_CATEGORIA");
            entity.Property(e => e.IdEditorial).HasColumnName("ID_EDITORIAL");
            entity.Property(e => e.RutaImgLibro)
                .HasMaxLength(200)
                .IsUnicode(false)
                .HasColumnName("RUTA_IMG_LIBRO");
            entity.Property(e => e.Stock).HasColumnName("STOCK");
            entity.Property(e => e.Titulo)
                .HasMaxLength(100)
                .IsUnicode(false)
                .HasColumnName("TITULO");

            entity.HasOne(d => d.IdAutorNavigation).WithMany(p => p.Libros)
                .HasForeignKey(d => d.IdAutor)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_AUTOR");

            entity.HasOne(d => d.IdCategoriaNavigation).WithMany(p => p.Libros)
                .HasForeignKey(d => d.IdCategoria)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_CATEGORIA");

            entity.HasOne(d => d.IdEditorialNavigation).WithMany(p => p.Libros)
                .HasForeignKey(d => d.IdEditorial)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_EDITORIAL");
        });

        modelBuilder.Entity<Persona>(entity =>
        {
            entity.HasKey(e => e.IdPersona).HasName("PK_ID_PERSONA");

            entity.ToTable("PERSONA");

            entity.HasIndex(e => e.Dni, "UQ__PERSONA__C035B8DD06B823ED").IsUnique();

            entity.HasIndex(e => e.Telefono, "UQ__PERSONA__D6F16945A9FE0CF8").IsUnique();

            entity.HasIndex(e => e.CorreoElec, "UQ__PERSONA__F354F2E0470FDB62").IsUnique();

            entity.Property(e => e.IdPersona).HasColumnName("ID_PERSONA");
            entity.Property(e => e.Apellido)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("APELLIDO");
            entity.Property(e => e.CorreoElec)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("CORREO_ELEC");
            entity.Property(e => e.Dni)
                .HasMaxLength(8)
                .IsUnicode(false)
                .IsFixedLength()
                .HasColumnName("DNI");
            entity.Property(e => e.IdDistrito).HasColumnName("ID_DISTRITO");
            entity.Property(e => e.IdTipoPersona).HasColumnName("ID_TIPO_PERSONA");
            entity.Property(e => e.Nombre)
                .HasMaxLength(20)
                .IsUnicode(false)
                .HasColumnName("NOMBRE");
            entity.Property(e => e.Telefono)
                .HasMaxLength(9)
                .IsUnicode(false)
                .IsFixedLength()
                .HasColumnName("TELEFONO");

            entity.HasOne(d => d.IdDistritoNavigation).WithMany(p => p.Personas)
                .HasForeignKey(d => d.IdDistrito)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_DISTRITO");

            entity.HasOne(d => d.IdTipoPersonaNavigation).WithMany(p => p.Personas)
                .HasForeignKey(d => d.IdTipoPersona)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_TIPO_PERSONA");
        });

        modelBuilder.Entity<RegistroLibrosEditorial>(entity =>
        {
            entity.HasKey(e => new { e.IdPersona, e.IdLibro }).HasName("PK_ID_REGISTRO");

            entity.ToTable("REGISTRO_LIBROS_EDITORIAL");

            entity.Property(e => e.IdPersona).HasColumnName("ID_PERSONA");
            entity.Property(e => e.IdLibro).HasColumnName("ID_LIBRO");
            entity.Property(e => e.CantidadLibros).HasColumnName("CANTIDAD_LIBROS");
            entity.Property(e => e.FechaRegistro)
                .HasColumnType("datetime")
                .HasColumnName("FECHA_REGISTRO");

            entity.HasOne(d => d.IdLibroNavigation).WithMany(p => p.RegistroLibrosEditorials)
                .HasForeignKey(d => d.IdLibro)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_LIBROS_EDITORIAL_REGIS");

            entity.HasOne(d => d.IdPersonaNavigation).WithMany(p => p.RegistroLibrosEditorials)
                .HasForeignKey(d => d.IdPersona)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_PERSONA_REGIS");
        });

        modelBuilder.Entity<ReservaVirtual>(entity =>
        {
            entity.HasKey(e => e.IdReserva);

            entity.ToTable("RESERVA_VIRTUAL");

            entity.Property(e => e.IdReserva).HasColumnName("ID_RESERVA");
            entity.Property(e => e.Estado).HasColumnName("ESTADO");
            entity.Property(e => e.FechaFinal)
                .HasColumnType("datetime")
                .HasColumnName("FECHA_FINAL");
            entity.Property(e => e.FechaInicio)
                .HasColumnType("datetime")
                .HasColumnName("FECHA_INICIO");
            entity.Property(e => e.IdPersona).HasColumnName("ID_PERSONA");

            entity.HasOne(d => d.IdPersonaNavigation).WithMany(p => p.ReservaVirtuals)
                .HasForeignKey(d => d.IdPersona)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_PERSONA_RESERVA");
        });

        modelBuilder.Entity<TipoPersona>(entity =>
        {
            entity.HasKey(e => e.IdTipoPersona).HasName("PK_ID_TIPO_PERSONA");

            entity.ToTable("TIPO_PERSONA");

            entity.HasIndex(e => e.Descripcion, "UQ__TIPO_PER__794449EF9BC18B16").IsUnique();

            entity.Property(e => e.IdTipoPersona).HasColumnName("ID_TIPO_PERSONA");
            entity.Property(e => e.Descripcion)
                .HasMaxLength(20)
                .IsUnicode(false)
                .HasColumnName("DESCRIPCION");
        });

        modelBuilder.Entity<Usuario>(entity =>
        {
            entity.HasKey(e => e.IdUsuario).HasName("PK_ID_USUARIO");

            entity.ToTable("USUARIO");

            entity.HasIndex(e => e.Username, "UQ__USUARIO__B15BE12EAB8837CF").IsUnique();

            entity.Property(e => e.IdUsuario)
                .ValueGeneratedNever()
                .HasColumnName("ID_USUARIO");
            entity.Property(e => e.Contrasena)
                .HasMaxLength(40)
                .IsUnicode(false)
                .HasColumnName("CONTRASENA");
            entity.Property(e => e.Estado).HasColumnName("ESTADO");
            entity.Property(e => e.Username)
                .HasMaxLength(30)
                .IsUnicode(false)
                .HasColumnName("USERNAME");

            entity.HasOne(d => d.IdUsuarioNavigation).WithOne(p => p.Usuario)
                .HasForeignKey<Usuario>(d => d.IdUsuario)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK_ID_USUARIO");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
