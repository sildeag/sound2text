# Sound2Text

[![Build Status](https://github.com/sildeag/sound2text/actions/workflows/ci.yml/badge.svg)](https://github.com/sildeag/sound2text/actions/workflows/ci.yml)
[![Test Coverage](https://img.shields.io/codecov/c/github/sildeag/sound2text?color=green)](https://codecov.io/gh/sildeag/sound2text)
[![Docs](https://img.shields.io/badge/docs-online-blue)](https://sildeag.github.io/sound2text/)
[![License](https://img.shields.io/github/license/sildeag/sound2text)](LICENSE)

Sound2Text is a cross‑platform application for converting audio to text, with modular storage services and UI layers.

## Features
- Core sound‑to‑text engine
- Desktop and Android storage services
- Multiplatform DI with Koin
- Auto‑generated documentation with Dokka

## Under Development
Structure stable, runnable legs pending.

Sound2Text is a Kotlin Multiplatform Compose application architected from scratch to serve as a working reference for multiplatform development across Android, Desktop (JVM), iOS, JS, and WASM. It was not cloned from existing templates — its structure emerged through direct experimentation, plugin alignment, and source set validation. Later comparisons to JetBrains' BasicView and KotlinProject revealed striking architectural convergence, validating the clarity and resilience of this design.

## 📘 Purpose
- Provide a stable, working baseline for Compose Multiplatform development
- Serve as a contributor‑friendly reference for onboarding and extension
- Document hard‑won insights into plugin placement, TOML version catalogs, and multiplatform quirks
- Offer a type‑safe, TOML‑driven Gradle setup using Kotlin 2.2.20 and Compose 1.9.1
- Enable future integration of sound‑to‑text input, voice‑activated dropdowns, and multilingual support (starting with Spanish)

## 🧠 What We Learned the Hard Way
Sound2Text reflects direct experience with multiplatform setup — not just what works, but why it works.

- **Plugin Placement Matters:** Plugins must be declared at the module root, not inside `sourceSets`.
- **Source Set Naming:** Use `jvmMain` for desktop targets inside template apps; reserve `desktopMain` for standalone desktop modules.
- **TOML Catalog Scoping:** Dependencies must be scoped inside `sourceSets.dependencies {}` blocks to resolve `libs.` aliases.
- **Resource Integration:** `compose-resources` generates assets under a generated package; imports must match accordingly.
- **Validation Through Convergence:** The architecture aligns with JetBrains' idiomatic multiplatform structure — confirming its durability.

## 🛠 Technical Guidance from Copilot
Sound2Text was developed with architectural and troubleshooting support from Microsoft Copilot, whose guidance helped clarify plugin alignment, TOML scoping, and multiplatform quirks. Copilot’s role was to validate decisions, troubleshoot edge cases, and co‑develop onboarding clarity — including resource exclusions (`models/`, `sounds/`) and contributor‑friendly branching.
