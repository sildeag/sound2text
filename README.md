# Sound2Text

[![Build Status](https://github.com/sildeag/sound2text/actions/workflows/ci.yml/badge.svg)](https://github.com/sildeag/sound2text/actions/workflows/ci.yml)
[![Test Coverage](https://img.shields.io/codecov/c/github/sildeag/sound2text?color=green)](https://codecov.io/gh/sildeag/sound2text)
[![Docs](https://img.shields.io/badge/docs-online-blue)](https://sildeag.github.io/sound2text/)
[![License](https://img.shields.io/github/license/sildeag/sound2text)](LICENSE)

Sound2Text is a cross‑platform application and audio‑to‑text pipeline built with Kotlin Multiplatform.  
It captures spoken input — from a microphone or from audio files — and converts it into structured text using pluggable speech‑to‑text engines.  
The project is designed to support real applications such as populating PDF form fields, capturing genealogical memories, recording medical notes, and enabling multilingual voice‑driven data entry.

Sound2Text is a Kotlin Multiplatform Compose application architected from scratch to serve as a working reference for multiplatform development across Android, Desktop (JVM), iOS, JS, and WASM.  
It was not cloned from existing templates — its structure emerged through direct experimentation, plugin alignment, and source‑set validation.  
Later comparisons to JetBrains' BasicView and KotlinProject revealed striking architectural convergence, validating the clarity and resilience of this design.

---

## What Sound2Text Is

Sound2Text provides a clean, practical foundation for speech‑driven workflows.  
It offers:

- live microphone capture
- file‑based audio processing
- a unified configuration system
- modular UI layers
- pluggable STT engines (starting with Vosk)
- CPU‑friendly operation suitable for everyday hardware

The architecture emphasizes clarity, modularity, and real‑world usefulness — not just a framework, but a tool that can support meaningful tasks.

---

## What Sound2Text Is Not

Sound2Text is not a full audio workstation, a GPU‑accelerated ML framework, or a research‑grade DSP toolkit.  
It does not aim to provide advanced audio editing, neural‑network training, or heavy signal‑processing features.

Its focus is practical and specific:  
**turning spoken words into usable text through a clean, extensible pipeline.**

---

## Why Sound2Text Exists

The project began with a simple, real‑world need:  
**to fill PDF forms using voice input in a fast, accurate, and distraction‑free way.**

From there, the concept expanded to support other human‑scale tasks:

- capturing family stories and genealogical memories
- recording medical notes for patient records
- enabling voice‑driven data entry
- supporting multilingual workflows (starting with English and Spanish)

Sound2Text aims to make speech input accessible and useful without requiring specialized hardware or heavyweight frameworks.

---

## Architectural Principles

Sound2Text is built around a set of guiding principles that keep the project maintainable and aligned with modern Kotlin development:

- **Shared logic in `core`**  
  Engines, settings, and abstractions live in a common module.

- **Platform‑specific audio capture in UI modules**  
  Java Sound on desktop, AudioRecord on Android, FFmpeg as a universal fallback.

- **Unified configuration (`config.json`)**  
  A single, typed source of truth for audio and STT settings.

- **Pluggable STT engines**  
  Vosk today, with the architecture ready for additional engines and multilingual support.

- **CPU‑friendly by design**  
  No GPU assumptions; optimized for typical desktops and laptops.

- **Composable pipeline**  
  Audio source → preprocessing → STT engine → structured output.

- **Modern Kotlin tooling**  
  Dependency injection patterns, unit testing, Dokka documentation, and JaCoCo coverage support long‑term clarity and reliability.

---

## Features

- Core sound‑to‑text engine
- Desktop and Android storage services
- Multiplatform DI with Koin
- Auto‑generated documentation with Dokka
- Unified configuration system
- Modular UI layers
- Typed Gradle/TOML version catalog
- Compose Multiplatform support across JVM, Android, iOS, JS, and WASM

---

## Under Development

Structure stable; runnable legs pending integration of the new configuration system, Vosk wiring, and desktop audio capture.

Sound2Text was architected from scratch as a working reference for Compose Multiplatform development.  
Its structure emerged through direct experimentation, plugin alignment, and source‑set validation — not from cloning templates.  
Later comparisons to JetBrains’ BasicView and KotlinProject revealed strong architectural convergence, validating the clarity and resilience of this design.

---

## 📘 Purpose

- Provide a stable, working baseline for Compose Multiplatform development
- Serve as a contributor‑friendly reference for onboarding and extension
- Document hard‑won insights into plugin placement, TOML version catalogs, and multiplatform quirks
- Offer a type‑safe, TOML‑driven Gradle setup using Kotlin 2.3.0 and Compose 1.9.3 and 2.2.21
- Enable future integration of sound‑to‑text input, voice‑activated dropdowns, and multilingual support (starting with Spanish)

---

## 🧠 What We Learned the Hard Way

Sound2Text reflects direct experience with multiplatform setup — not just what works, but *why* it works.

- **Plugin Placement Matters:** Plugins must be declared at the module root, not inside `sourceSets`.
- **Source Set Naming:** Use `jvmMain` for desktop targets inside template apps; reserve `desktopMain` for standalone desktop modules.
- **TOML Catalog Scoping:** Dependencies must be scoped inside `sourceSets.dependencies {}` blocks to resolve `libs.` aliases.
- **Resource Integration:** `compose-resources` generates assets under a generated package; imports must match accordingly.
- **Validation Through Convergence:** The architecture aligns with JetBrains' idiomatic multiplatform structure — confirming its durability.

---

## 🛠 Technical Guidance from Copilot

Sound2Text was developed with architectural and troubleshooting support from Microsoft Copilot, whose guidance helped clarify plugin alignment, TOML scoping, and multiplatform quirks.  
Copilot assisted in validating decisions, troubleshooting edge cases, and shaping contributor‑friendly onboarding — including resource exclusions (`models/`, `sounds/`) and branching strategy.

## 📚 Documentation & Wiki

The Sound2Text wiki contains deeper technical notes, architectural diagrams, module explanations, and ongoing development insights.

👉 **Visit the wiki:** https://github.com/sildeag/sound2text/wiki
